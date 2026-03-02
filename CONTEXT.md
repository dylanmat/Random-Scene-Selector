# Context for Random Scene Selector (Hubitat App)

This file is the authoritative source of truth for:
- Intent/scope
- Constraints and non-goals
- Implementation decisions
- Clarifications/corrections (append-only)
- Repo rules (README/CHANGE/CONTEXT/TODO)

If a question is answered here, it should not be asked again.

---

## 1) App identity
**App Name:** Random Scene Selector  
**Acronym:** rss  
**Namespace rule (always):** `dylanm.rss`  
**Child namespace rule (always):** `dylanm.rss.child`

**Parent filename:** `app/RandomSceneSelectorParent.groovy`  
**Child filename:** `app/RandomSceneSelectorChild.groovy`

---

## 2) Problem statement
Building random scene selectors for Hue scenes in Hubitat is onerous and complicated with existing tooling.
This app simplifies it by creating a dedicated child "Scene Activator" button per room/group that randomly
selects from a list of Hue scenes and activates one with a chosen Hue mode. It also flips override switches ON
to prevent other automations from immediately overriding the selected scene.

---

## 3) In-scope
- Parent app can create/edit/delete child app instances
- Child app creates a child device that behaves like a button (`<Name> Scene Activator`)
- Child device button press triggers:
  - Turn ON configured override switches
  - Randomly select one Hue scene from configured list
  - Activate the selected scene using configured Hue mode (1-9)
- UI prompts in child creation/config:
  - Random selector name
  - Override switches
  - Hue scene mode (1-9)
  - Scenes list (hueBridgeScene devices)

## 4) Out-of-scope / Non-goals
- Managing Hue scene definitions themselves
- Building complex logic/rules beyond random selection + activation
- Automatically turning override switches back OFF (unless added later)
- Multi-button behavior beyond single activator press

---

## 5) Expected behavior (canonical)
### Child naming rule
- User enters Random Selector Name, e.g. `Office`
- Created device name becomes: `Office Scene Activator`
- Preserve user spacing/casing; trim leading/trailing spaces

### Override switches
- Selected override switches are turned ON whenever the activator button is pressed.
- Purpose: prevent normal programming from taking over immediately.

### Hue scene mode (1-9)
User selects one integer 1-9 representing Hue scene activation mode:
1: Default  
2: Dynamic palette  
3: Static  
4: Dynamic palette, custom duration  
5: Static, custom duration  
6: Dynamic palette, custom brightness  
7: Static, custom brightness  
8: Dynamic palette, custom duration and brightness  
9: Static, custom duration and brightness

Current implementation path:
- Child app uses `sceneOn(hueMode as Integer)` if available.
- Fallback to `on()` if `sceneOn` is unavailable.

### Scene selection
- User selects a list of Hue scenes (device type: hueBridgeScene)
- Button press picks one at random from configured list
- If scene list is empty:
  - Do nothing and log warning

---

## 6) Architecture
### App structure
- Main app responsibilities:
  - Display list of child instances
  - Create/edit/delete child instances
  - Install/updated housekeeping
- Child app responsibilities:
  - Preferences UI for selector instance
  - Create/update child device
  - Wire button press to handler
  - Implement random selection + activation + override switch ON behavior

### Child device approach
- Use built-in `Virtual Button` device type created per child instance.
- Button mapping:
  - Button 1 = Activate random scene

### State model
- settings:
  - selectorName
  - overrideSwitches[]
  - hueMode (1-9)
  - scenes[]
- state:
  - lastSelectedSceneId
  - lastActivatedAt

### Scheduling / subscriptions
- Subscription:
  - child device `pushed` event -> handler
- No periodic schedules required

---

## 7) Coding conventions (Hubitat Groovy)
- UI separated from logic
- Single logging helper with debug/info/warn/error
- Guard rails:
  - If disabled, do nothing
  - If scenes list empty, do nothing
  - Defensive checks for null devices
- Keep event handler thin

---

## 8) Repo/documentation rules (hard requirements)
- README.md always current for install/config/usage
- CHANGE.md updated for every meaningful change
- CONTEXT.md updated with clarifications/corrections so they never come up again
- TODO.md tracks known bugs + work items

---

## 9) Clarifications & Corrections Log (append-only)

### 2026-03-01
- Clarification: Parent app allows selecting hueBridgeScene devices and children randomly activate scenes from a configured list.
- Clarification: Child creation prompts for selector name, override switches to flip ON, a Hue mode number (1-9), and a list of scenes.
- Clarification: Namespace always `dylanm.rss`, child uses `.child`.

### 2026-03-02
- Clarification: Main app label does not need to include the word Parent.
- Clarification: Main app namespace does not need `.parent`; use `dylanm.rss`.

### 2026-03-02 (v0.1.1)
- Correction: `definition(...)` must be the first declaration in Hubitat app files for parent/child metadata to register reliably.
- Correction: Child app remains non-standalone via `parent: 'dylanm.rss:Random Scene Selector'` and should not be installed directly from Install User App.
