# ARCHITECTURE

## Architecture Principles
- Keep the Hubitat app small and focused on random Hue scene activation.
- Keep the parent app responsible for child lifecycle only.
- Keep the child app responsible for selector configuration, virtual button creation, and activation behavior.
- Prefer defensive no-op behavior over runtime failure when configuration is incomplete.
- Preserve Hubitat metadata ordering and namespace contracts.

## Component Model
### Parent App
File: [app/RandomSceneSelectorParent.groovy](app/RandomSceneSelectorParent.groovy)

Responsibilities:
- Register the `Random Scene Selector` user app.
- Present the parent configuration page.
- Create, edit, and delete `Random Scene Selector Child` app instances.
- Run install/update housekeeping.

### Child App
File: [app/RandomSceneSelectorChild.groovy](app/RandomSceneSelectorChild.groovy)

Responsibilities:
- Present selector configuration.
- Update the child app label from `Random Selector Name`.
- Create or update the selector virtual button device.
- Subscribe to the button `pushed` event.
- Turn on override switches.
- Pick and activate a random configured scene.
- Track last selected scene id and activation timestamp in state.

### Generated Device
- Device type: Hubitat built-in `Virtual Button`.
- Device network id: `rss-activator-${app.id}`.
- Label: `<Random Selector Name> Scene Activator`.
- Button mapping: Button 1 activates a random scene.

## Data Flow
1. User installs the parent app in Hubitat.
2. User creates a child selector from the parent app.
3. Child selector saves settings:
   - `selectorName`
   - `overrideSwitches`
   - `hueMode`
   - `scenes`
   - `enableDebug`
4. Child initialization creates or updates the virtual button and subscribes to `pushed`.
5. Button push triggers `handlePushed`.
6. The child app picks a random scene from `scenes`.
7. The app turns on configured override switches.
8. The app calls `sceneOn(hueMode)` when available, otherwise `on()`.
9. The app records `lastSelectedSceneId` and `lastActivatedAt`.

## Integration Points
- Hubitat parent/child app framework.
- Hubitat built-in `Virtual Button` driver.
- Hubitat switch capability for override switches.
- Hubitat Hue Bridge integration scene devices.

## State Model
### Settings
- `selectorName`: required selector display name.
- `overrideSwitches`: optional switch devices.
- `hueMode`: enum value from `1` through `9`.
- `scenes`: required list of scene actuator devices.
- `enableDebug`: optional debug logging toggle.

### State
- `lastSelectedSceneId`: id of the last selected scene device.
- `lastActivatedAt`: timestamp from `now()` for the last activation.

## Error Handling and Degradation
- Empty scene list: log warning and do nothing.
- Null devices: filtered out before use where practical.
- Missing `sceneOn`: fall back to `on()`.
- Scene command exception: log warning and continue without throwing.
- Debug logs are suppressed unless `enableDebug` is enabled.

## Quality Gates
- Static sanity check of Groovy syntax where practical.
- Manual Hubitat install/update verification for parent and child app metadata.
- Manual selector creation and button push verification after behavior changes.
- Documentation updates for any behavior, configuration, integration, or version changes.

## Non-AI Project Note
This repository uses the AI project framework for development workflow and documentation. The product itself is a Hubitat Groovy app, not an AI runtime service. AI-specific framework sections should be interpreted as agent workflow guidance unless future product behavior adds AI features.
