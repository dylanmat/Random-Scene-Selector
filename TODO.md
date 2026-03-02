# TODO / Known Issues / Tech Debt

## P0 — Must fix
- [ ] Validate exact Hue scene command behavior for all Hue integration versions

## P1 — Should do soon
- [ ] Confirm mode mapping 1-9 against Hubitat Hue Bridge implementation details
- [ ] Decide whether to auto-rename child device after selector name changes for existing automations
- [ ] Add optional user notification when scene list is empty

## P2 — Nice to have
- [ ] Optional: prevent repeating the same scene twice in a row
- [ ] Optional: turn off override switches after X minutes
- [ ] Optional: expose Last Activated Scene as attribute for dashboards
- [ ] Optional: add Next Scene / Previous Scene deterministic mode

## Investigations
- [ ] Edge case: Hue scenes list includes unavailable/offline devices
- [ ] Edge case: override switches already ON (should be no-op)


## Recently resolved
- [x] Child app parent linkage error (404 has no parent) fixed in v0.1.1
