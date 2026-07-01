# CONTEXT

## System Purpose
Random Scene Selector reduces the effort required to build random Philips Hue scene selectors in Hubitat. It creates a selector child app per room or group, and each child creates a dedicated virtual button device that activates one randomly chosen configured Hue scene.

The app also turns on configured override switches before scene activation so other automations do not immediately override the selected scene.

## Users & Stakeholders
- Primary users: Hubitat users managing Hue scenes by room or group.
- Maintainers: project owner and AI coding agents working under the root documentation framework.
- Dependencies: Hubitat app runtime, Hubitat Hue Bridge integration, Hue scene devices exposed to Hubitat.

## Operational Context
- Runtime environment: Hubitat user app runtime.
- Implementation language: Hubitat Groovy.
- Parent app file: [app/RandomSceneSelectorParent.groovy](app/RandomSceneSelectorParent.groovy).
- Child app file: [app/RandomSceneSelectorChild.groovy](app/RandomSceneSelectorChild.groovy).
- External integrations: Philips Hue via Hubitat Hue Bridge integration.
- Tested Hubitat firmware versions: TBD - owner confirmation required.

## Domain Vocabulary
- Parent app: `Random Scene Selector`, installed once in Hubitat and used to manage selector children.
- Child app: `Random Scene Selector Child`, a parent-only child app instance for one room/group selector.
- Scene activator: the virtual button device created by a child app.
- Override switch: a switch turned on before scene activation to keep other automations from taking control immediately.
- Hue mode: the integer 1-9 passed to `sceneOn(mode)` when supported by the selected scene device.
- `hueBridgeScene`: Hubitat Hue Bridge scene device behavior expected by this app.

## Current State
- Version `0.2.0` is implemented in the Hubitat app files.
- The parent app creates, edits, and deletes child selector instances.
- The child app creates or updates a virtual button named `<Random Selector Name> Scene Activator`.
- Button push handling turns on configured override switches, picks a random configured scene, and activates it.
- The child app records `state.lastSelectedSceneId` and `state.lastActivatedAt`.

## In Scope
- Parent app child instance management.
- Child app selector configuration.
- Virtual Button child device creation and label updates.
- Single activator button behavior.
- Random scene selection from configured scenes.
- Optional no-repeat random scene selection.
- Hue scene activation using modes 1-9.
- Override switch activation.

## Out of Scope
- Managing Hue scene definitions.
- Complex rule logic beyond random selection and activation.
- Automatically turning override switches back off unless added as a future feature.
- Multi-button behavior beyond the current single activator press.

## Expected Behavior
### Child Naming
The user-entered Random Selector Name is trimmed and used for the child app label. The generated device label is `<Name> Scene Activator`. User spacing and casing inside the trimmed name are preserved.

### Button Mapping
Button 1 activates a random configured scene.

### Scene Activation
The child app chooses one configured scene at random. If no scenes are configured, it does nothing and logs a warning. If the scene device supports `sceneOn`, the app calls `sceneOn(hueMode as Integer)`. Otherwise, it falls back to `on()`.

If `avoidRepeat` is enabled and two or more scenes are configured, the child app excludes `state.lastSelectedSceneId` from the next random candidate list. If only one scene is configured, or the previous scene is no longer configured, selection falls back to the current configured list.

### Hue Scene Modes
- `1`: Default
- `2`: Dynamic palette
- `3`: Static
- `4`: Dynamic palette, custom duration
- `5`: Static, custom duration
- `6`: Dynamic palette, custom brightness
- `7`: Static, custom brightness
- `8`: Dynamic palette, custom duration and brightness
- `9`: Static, custom duration and brightness

## Known Limitations and Risks
- Exact `sceneOn` behavior needs validation across Hubitat Hue integration versions.
- Mode mapping 1-9 needs confirmation against Hubitat Hue Bridge implementation details.
- Behavior with unavailable/offline Hue scene devices needs investigation.
- Existing automation impact of renaming child devices needs a product decision.

## Success Signals
- Parent and child apps register correctly in Hubitat.
- Child instances can be created from the parent app without parent linkage errors.
- Each child app creates a working virtual button device.
- A button push turns on configured override switches and activates one configured scene.
- Empty scene lists fail safely with a warning and no scene activation.
- Documentation and changelog entries stay aligned with behavior changes.

## Guardrails
- Keep namespace values stable: parent `dylanm.rss`, child `dylanm.rss.child`.
- Keep the child app parent-only via `parent: 'dylanm.rss:Random Scene Selector'`.
- Keep `definition(...)` first in Hubitat app files.
- Do not log credentials or sensitive local environment details.
- Do not change user-facing behavior without updating `README.md`, `CONTEXT.md`, `ROADMAP.md`, and `CHANGELOG.md` as applicable.

## Clarifications & Corrections Log
### 2026-03-01
- Parent app allows selecting Hue scene devices and children randomly activate scenes from a configured list.
- Child creation prompts for selector name, override switches to flip on, a Hue mode number from 1-9, and a scene list.
- Namespace is always `dylanm.rss`; child namespace is `dylanm.rss.child`.

### 2026-03-02
- Main app label does not need to include the word Parent.
- Main app namespace does not need `.parent`; use `dylanm.rss`.

### 2026-03-02 (v0.1.1)
- `definition(...)` must be the first declaration in Hubitat app files for parent/child metadata to register reliably.
- Child app remains non-standalone via `parent: 'dylanm.rss:Random Scene Selector'` and should not be installed directly from Add User App.

### 2026-07-01 (v0.2.0)
- Optional no-repeat random selection is controlled per child selector with `avoidRepeat`.
- v0.2.0 keeps the built-in Hubitat `Virtual Button`; custom driver, dashboard attribute, and next/previous controls are deferred to v0.2.5.

## Pointers
- High-level overview: [README.md](README.md)
- System design/details: [ARCHITECTURE.md](ARCHITECTURE.md)
- Security expectations: [SECURITY.md](SECURITY.md)
- Coding/testing/review conventions: [STANDARDS.md](STANDARDS.md)
- Decision history: [DECISIONS.md](DECISIONS.md)
- Roadmap and TODOs: [ROADMAP.md](ROADMAP.md)
- Release notes: [CHANGELOG.md](CHANGELOG.md)
