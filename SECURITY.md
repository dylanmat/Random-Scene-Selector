# SECURITY

## Security Scope and Ownership
This policy covers the Random Scene Selector repository, Hubitat app code, documentation, and AI-assisted development workflow. Project maintainers own security decisions and policy updates.

## Credential Handling Policy
- Do not commit Hubitat credentials, Hue credentials, tokens, keys, local hub URLs, or private network details.
- Do not place credentials or private environment details in logs, prompts, screenshots, issues, or pull requests.
- Use local-only storage for any development credentials if they become necessary.
- Rotate any credential immediately if it is accidentally exposed.

## Data Access and Classification Policy
- Public: repository documentation and app code intended for sharing.
- Internal: local development notes, hub topology, room names, device names, and automation details.
- Sensitive: credentials, tokens, private hub addresses, and private network details.
- Restricted: production-like access to a user's Hubitat hub or home automation environment.

AI agents and contributors should avoid collecting or reproducing private device inventories unless explicitly needed for a task.

## AI Restrictions and Safety Boundaries
- AI agents must not invent tested Hubitat versions, security posture, license terms, or production validation evidence.
- AI agents must not make unreviewed changes that could silently alter home automation behavior.
- Human review is required before changes that affect device control, scheduling, or switch/scene activation semantics are treated as release-ready.
- Generated code must preserve namespace and parent/child registration constraints unless an explicit architectural decision supersedes them.

## Environment and Deployment Controls
- Hubitat deployment is manual through Apps Code unless a future documented release process supersedes this.
- Keep parent and child app versions aligned when behavior changes.
- Do not embed environment-specific configuration in Groovy source.
- Keep release notes and roadmap status current for behavior-affecting changes.

## Incident Reporting and Response
- Report suspected security or safety issues to the project owner.
- Treat credential exposure, unintended device activation, and unsafe automation behavior as high priority.
- Remediate by removing exposed data, rotating affected credentials, documenting impact, and updating tests/docs where applicable.

## Compliance Notes
No regulatory or contractual compliance requirements are currently documented for this project. Add them here if project scope changes.
