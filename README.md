# Random Scene Selector

Hubitat parent/child app set for creating virtual scene activator buttons that trigger a random Hue scene.

## Basic Use

1. Install the parent and child app groovy files.
2. Install **Random Scene Selector Parent** in Hubitat Apps.
3. Add one or more child selector apps from the parent page.
4. In each child app configure:
   - `selectorName`
   - optional `overrideSwitches`
   - `hueMode` (1 through 9)
   - one or more Hue scene devices
5. Use the created `"<selectorName> Scene Activator"` virtual button device. A button 1 press turns on override switches and activates one random scene.

## Changelog

### 0.1.0
- Added parent app dynamic management page for child apps.
- Added child app configuration, activator device creation, event subscription, and random scene activation flow with defensive logging.
