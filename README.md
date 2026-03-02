# Random Scene Selector

Random Scene Selector is a Hubitat parent/child app set that lets you define scene groups and randomly activate one scene from a group when triggered.

## Purpose

- Provide a reusable parent app for managing child scene selectors.
- Let each child app target a set of scenes and choose one at random.
- Keep configuration simple and easy to maintain.

## Namespaces

- Parent app namespace: `dylanm.rss`
- Child app namespace: `dylanm.rss.child`

## Installation

1. In Hubitat, go to **Apps Code**.
2. Create a new app and paste `app/RandomSceneSelectorParent.groovy`.
3. Save the parent app.
4. Create a second app and paste `app/RandomSceneSelectorChild.groovy`.
5. Save the child app.
6. Go to **Apps** and add **Random Scene Selector (Parent)**.

## Configuration

1. Open the parent app.
2. Create one or more child apps.
3. In each child app, configure:
   - A name/label.
   - Scene devices to choose from.
   - A trigger (manual/run-invoked now; automation wiring to be expanded).

## Usage

- Use each child app as an independent random scene chooser.
- Trigger the child app action from dashboards, rules, or future exposed commands.
- Review `TODO.md` for planned enhancements and known gaps.

## Project Files

- `CONTEXT.md` - canonical requirements and clarifications.
- `TODO.md` - known issues and work items.
- `CHANGE.md` - change history and version notes.

## Changelog

See `CHANGE.md` for full version history.
