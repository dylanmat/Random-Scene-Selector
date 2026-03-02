# Changelog

## [0.1.2] - 2026-03-02
### Fixed
- Hue Scene Mode selector now shows descriptive labels for options 1-9 directly in the dropdown.
- Child app instances now update their app label to match `Random Selector Name`, so parent app child listings are identifiable by name.

## [0.1.1] - 2026-03-02
### Fixed
- Fixed child app parent metadata registration issue by making `definition(...)` the first declaration in app files, so child creation no longer throws "has no parent".
- Kept child app as parent-only (`parent: dylanm.rss:Random Scene Selector`) so it is not directly installable from Install User App.

### Changed
- Parent child-creation button title now reads `Add a Random Scene Selector`.

## [0.1.0] - 2026-03-02
### Added
- Initial documentation set: README, CONTEXT, TODO, CHANGE
- Main app implementation for child instance management
- Child app implementation for virtual button creation and random scene activation

### Changed
- Main app naming clarified to not require "Parent" label
- Namespace clarified and implemented as `dylanm.rss` for main app and `dylanm.rss.child` for child app

### Fixed
- N/A (initial implementation)
