# STANDARDS

## Coding Standards
- Use Hubitat-compatible Groovy for app code.
- Keep `definition(...)` as the first declaration in each Hubitat app file.
- Keep parent namespace `dylanm.rss` and child namespace `dylanm.rss.child` unless an ADR explicitly changes them.
- Keep UI definitions readable and close to the app settings they create.
- Keep event handlers thin; move behavior into helper methods when it grows.
- Use defensive checks for optional devices and missing commands.
- Preserve user-entered selector casing and internal spacing; trim leading and trailing spaces.

## Logging Standards
- Use a single logging helper for app logs.
- Suppress debug logs unless the selector enables debug logging.
- Do not log credentials, private hub URLs, or unnecessary device inventory.
- Warn instead of throwing for recoverable runtime issues such as empty scene lists or scene command failure.

## Testing and Verification Standards
- For behavior changes, verify parent app registration, child app creation, virtual button creation, and button push behavior in Hubitat.
- For Hue behavior changes, record the Hubitat firmware version and Hue integration behavior observed.
- Where automated checks are practical, run syntax/static checks before release.
- Record key verification evidence in pull requests or release notes.

## Documentation Standards
- Keep root docs current: `README.md`, `CONTEXT.md`, `ARCHITECTURE.md`, `SECURITY.md`, `STANDARDS.md`, `DECISIONS.md`, `ROADMAP.md`, `CHANGELOG.md`, and `AGENTS.md`.
- Keep `README.md` focused on installation, configuration, usage, and current capabilities.
- Keep `CONTEXT.md` as the canonical source for app intent, scope, guardrails, and clarifications.
- Track feature plans by version in `ROADMAP.md`.
- Track bugs, defects, investigations, and housekeeping separately as TODO items in `ROADMAP.md`.
- Update `CHANGELOG.md` for every meaningful user-facing or workflow-facing change.
- Record architectural decisions and major tradeoffs in `DECISIONS.md`.

## Review Standards
- Review behavior changes for Hubitat registration, parent/child linkage, generated device behavior, and automation side effects.
- Include summary, rationale, and verification evidence in pull requests.
- Do not approve unresolved critical findings related to unintended device control or broken app registration.

## Operational Standards
- Deployment is by copying Groovy app code into Hubitat Apps Code unless documented otherwise.
- Child app code should remain parent-only.
- Version-facing changes should update app descriptions, changelog entries, and roadmap status together.
- PowerShell is acceptable and preferred for Windows automation paths in this repository.

## Frontend Standards
For any plain `html/css/js` interface added later, prefer Bootstrap before custom CSS.
