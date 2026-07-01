# ROADMAP

## Purpose
Track project development priorities, sequencing, and delivery status.

Legitimate feature changes are grouped by semantic version. Bugs, defects, investigations, and housekeeping are tracked separately as TODO items. Work currently underway remains under Unreleased.

## Unreleased
### Documentation Framework Retrofit
- Type: Housekeeping
- Status: In Progress
- Owner: Project Maintainers
- Goal: complete the migration from legacy project docs into the AI project framework.
- Success Criteria:
  - Legacy docs are merged into the required root docs.
  - Superseded legacy docs are removed.
  - Remaining unknowns are clearly identified for owner confirmation.

## Versioned Feature Plan
### v0.2.0 - Scene Selection Controls
- Status: Proposed
- Feature candidates:
  - Prevent repeating the same scene twice in a row.
  - Expose Last Activated Scene as an attribute for dashboards.
  - Add Next Scene / Previous Scene deterministic mode.
- Acceptance Criteria:
  - Behavior is configurable per selector.
  - Existing random activation behavior remains the default.
  - README, CONTEXT, CHANGELOG, and tests or manual verification notes are updated.

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
