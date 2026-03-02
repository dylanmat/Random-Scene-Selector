# Random Scene Selector

Random Scene Selector is a Hubitat Groovy app split into a parent app and child app.

## Basic use

1. Install the parent app and child app Groovy files in Hubitat.
2. Open the parent app to create and manage child app instances.
3. Configure each child app to select and run your target scene logic.

## Versioning policy

Single source of truth for version is the `version` field in each Groovy app `definition` metadata block:

- `apps/RandomSceneSelectorParent.groovy`
- `apps/RandomSceneSelectorChild.groovy`

`CHANGE.md` mirrors each released version entry and should always match the app metadata version.

### How to bump versions

- Patch (`0.0.1`) for minor fixes.
- Minor (`0.1.0`) for medium changes.
- Major (`1.0.0`) for breaking changes.

## Pre-PR checklist

Before opening a PR:

- [ ] Bump `version` in both parent and child app `definition` metadata.
- [ ] Add/update the matching release section in `CHANGE.md`.
- [ ] Confirm README versioning guidance still matches project practice.
