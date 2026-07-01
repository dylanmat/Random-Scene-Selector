# Random Scene Selector

Random Scene Selector is a Hubitat parent/child app that creates per-room or per-group scene activator button devices for Philips Hue scenes exposed through Hubitat.

When a scene activator button is pressed, the child app turns on configured override switches, chooses one configured Hue scene at random, and activates it with the selected Hue mode.

## Project Identity
- App name: Random Scene Selector
- Acronym: `rss`
- Parent namespace: `dylanm.rss`
- Child namespace: `dylanm.rss.child`
- Type: Hubitat parent app plus child app
- Current app version: `0.2.0`
- Hubitat platform: TBD - owner confirmation required for tested hub firmware versions
- Integration: Philips Hue through the Hubitat Hue Bridge integration, using `hueBridgeScene` devices
- License: TBD - owner confirmation required

## Audience
- Hubitat users who want a lightweight way to randomize Hue scene activation by room or group.
- Project maintainers and AI coding agents updating the Hubitat app and its documentation.

## Core Capabilities
- Parent app manages child selector instances.
- Each child selector creates a dedicated `<Name> Scene Activator` virtual button device.
- Button 1 activates one random scene from the selector's configured scene list.
- Configured override switches are turned on before the selected scene is activated.
- Hue scene mode can be selected from modes 1 through 9.

## Requirements
- Hubitat hub with user app support.
- Hubitat Hue Bridge integration installed and working.
- One or more Hue scenes exposed as devices compatible with `hueBridgeScene` behavior.

## Installation
1. In Hubitat, open Apps Code.
2. Create a new app from [app/RandomSceneSelectorParent.groovy](app/RandomSceneSelectorParent.groovy).
3. Create a new app from [app/RandomSceneSelectorChild.groovy](app/RandomSceneSelectorChild.groovy).
4. Open Apps, choose Add User App, and select Random Scene Selector.
5. Create a selector child instance for each room or group.

The child app is parent-only and should not be installed directly from Add User App.

## Configuration
### Parent App
- Add, edit, and delete random scene selector child instances.

### Selector Child App
- Random Selector Name: room/group label such as `Office`.
- Override Switches: optional switches turned on before scene activation.
- Hue Scene Mode (1-9):
  - `1`: Default
  - `2`: Dynamic palette
  - `3`: Static
  - `4`: Dynamic palette, custom duration
  - `5`: Static, custom duration
  - `6`: Dynamic palette, custom brightness
  - `7`: Static, custom brightness
  - `8`: Dynamic palette, custom duration and brightness
  - `9`: Static, custom duration and brightness
- Scenes to randomize: Hue scene devices to choose from.
- Avoid repeating the last scene: optional; when enabled and two or more scenes are configured, the next random pick excludes the previously activated scene.
- Enable debug logging: optional diagnostic logging.

## Usage
Press the `<Name> Scene Activator` button from Hubitat, a dashboard, or an automation.

The child app turns on configured override switches, picks a random configured scene, and attempts to call `sceneOn(mode)`. If the selected scene device does not expose `sceneOn`, the app falls back to `on()`.

If Avoid repeating the last scene is enabled, selectors with two or more scenes skip the previously activated scene when choosing the next random scene. Selectors with one scene continue activating that scene normally.

## Development
This project is being retrofitted into the AI project framework. Required root docs are:
- [CONTEXT.md](CONTEXT.md): system context and guardrails.
- [ARCHITECTURE.md](ARCHITECTURE.md): app structure, data flow, and integration blueprint.
- [SECURITY.md](SECURITY.md): credential, data, and automation safety policy.
- [STANDARDS.md](STANDARDS.md): coding, testing, review, and documentation standards.
- [DECISIONS.md](DECISIONS.md): architecture decision records.
- [ROADMAP.md](ROADMAP.md): versioned feature plans and separate TODO tracking.
- [CHANGELOG.md](CHANGELOG.md): release history.
- [AGENTS.md](AGENTS.md): AI agent workflow rules.
