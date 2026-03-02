# Random Scene Selector (Hubitat App)

**Acronym:** rss  
**Namespace:** `dylanm.rss`  
**Child Namespace:** `dylanm.rss.child`  
**Type:** Parent App + Child App (creates a child device)  
**Version:** 0.1.1  
**Hubitat Platform:** <BLANK: tested hub firmware version(s)>  
**Integrations:** Philips Hue via Hubitat Hue Bridge integration (hueBridgeScene devices)

## What this app does
Random Scene Selector creates a per-room (or per-group) "Scene Activator" button device that, when pressed,
randomly activates one scene from a user-selected list of Hue scenes (hueBridgeScene devices). It also flips
one or more "override" switches ON to prevent other automations from immediately overriding the chosen scene.

## Key features
- Parent app manages child instances (create/edit/delete)
- Each child instance creates a dedicated button device:
  - If named "Office", creates "Office Scene Activator"
- Button press:
  - Turns ON selected override switches
  - Selects a random Hue scene from configured list
  - Activates the scene using a configured Hue mode (1–9)
- Simple configuration focused on Hue scenes

## Requirements
- Hue integration installed and working
- One or more Hue scenes exposed as device type `hueBridgeScene`

## Installation
1. Hubitat UI -> Apps Code -> New App -> paste `app/RandomSceneSelectorParent.groovy` -> Save
2. Apps Code -> New App -> paste `app/RandomSceneSelectorChild.groovy` -> Save
3. Hubitat UI -> Apps -> Add User App -> select **Random Scene Selector**
4. In the app, create a new selector child instance for each room/group.

## Configuration
### Main app
- Add/edit/delete child instances

### Selector child app (per selector)
- **Random Selector Name**: e.g. `Office`
- **Override Switches**: switches turned ON when activator pressed
- **Hue Scene Mode (1-9)**:
  1. Default
  2. Dynamic palette
  3. Static
  4. Dynamic palette, custom duration
  5. Static, custom duration
  6. Dynamic palette, custom brightness
  7. Static, custom brightness
  8. Dynamic palette, custom duration and brightness
  9. Static, custom duration and brightness
- **Scenes to randomize**: list of hueBridgeScene devices

## Usage
- Press the `<Room> Scene Activator` button
  - Override switches turn ON
  - A random scene is activated (mode applied)
- Optional: integrate the activator button into dashboards and automations

## Development and maintenance
- Design decisions and clarifications are in `CONTEXT.md`
- Known issues are tracked in `TODO.md`
- Changes are tracked in `CHANGE.md`
- Every branch/PR update increments app version and updates changelog

## License
<BLANK>
