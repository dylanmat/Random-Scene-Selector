# ROADMAP

## Purpose
Track project development priorities, sequencing, and delivery status.

Legitimate feature changes are grouped by semantic version. Bugs, defects, investigations, and housekeeping are tracked separately as TODO items. Work currently underway remains under Unreleased.

## Unreleased
No active unreleased work.

## Versioned Feature Plan
### v0.2.0 - Documentation Retrofit + No-Repeat Random Selection
- Status: Active
- Owner: Project Maintainers
- Release Date: 2026-07-01
- Scope:
  - Incorporate the documentation framework retrofit.
  - Add optional no-repeat random scene selection.
  - Keep the built-in Hubitat `Virtual Button`.
- Acceptance Criteria:
  - `Avoid repeating the last scene` is configurable per selector and defaults off.
  - Existing random activation behavior remains the default.
  - With no-repeat enabled and two or more configured scenes, the next random selection excludes the last activated scene.
  - With no-repeat enabled and one configured scene, the single scene still activates normally.
  - README, CONTEXT, ARCHITECTURE, CHANGELOG, and manual verification notes are updated.

### v0.2.5 - Scene Activator Driver and Deterministic Controls
- Status: Proposed
- Feature candidates:
  - Add a custom Scene Activator driver.
  - Expose Last Activated Scene as an attribute for dashboards.
  - Add Next Scene / Previous Scene deterministic controls.
- Acceptance Criteria:
  - Installation instructions include the custom driver.
  - Existing button 1 random activation behavior is preserved unless explicitly changed by an ADR.
  - Dashboard attributes and deterministic controls are documented and manually verified.

### v0.3.0 - Override Switch Lifecycle
- Status: Proposed
- Feature candidates:
  - Optionally turn off override switches after a configured number of minutes.
- Acceptance Criteria:
  - Auto-off is disabled by default.
  - Selector configuration clearly labels timing behavior.
  - Scheduled cleanup does not affect switches that were not configured for the selector.

## TODO Items
### P0 - Must Fix
- [ ] Validate exact Hue scene command behavior for all Hue integration versions.

### P1 - Should Do Soon
- [ ] Confirm mode mapping 1-9 against Hubitat Hue Bridge implementation details.
- [ ] Decide whether to auto-rename child devices after selector name changes for existing automations.
- [ ] Add optional user notification when scene list is empty.

### P2 - Nice to Have
- [ ] Confirm tested Hubitat firmware versions for README and CONTEXT.
- [ ] Confirm project license for README.

### Investigations
- [ ] Edge case: Hue scenes list includes unavailable/offline devices.
- [ ] Edge case: override switches already on should be a no-op.

## Recently Resolved
- [x] Child app parent linkage error (`404 has no parent`) fixed in v0.1.1.
