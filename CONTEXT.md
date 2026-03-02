# Context

## Canonical Requirements

1. Maintain a baseline repository structure with:
   - `README.md`
   - `CHANGE.md`
   - `CONTEXT.md`
   - `TODO.md`
   - `app/RandomSceneSelectorParent.groovy`
   - `app/RandomSceneSelectorChild.groovy`
2. Keep Hubitat app definitions valid and explicit for both parent and child apps.
3. Use namespace identifiers exactly as follows:
   - Parent: `dylanm.rss`
   - Child: `dylanm.rss.child`
4. Keep documentation current for purpose, installation, configuration, and usage.
5. Track known issues/work in `TODO.md` and maintain change history in `CHANGE.md`.

## Clarifications

- This baseline establishes app skeletons and metadata; full runtime logic is intentionally minimal.
- Parent app is responsible for hosting child app instances.
- Child app provides the unit of random scene selection behavior.
- Versioning should be incremented on branch updates and pull requests.
