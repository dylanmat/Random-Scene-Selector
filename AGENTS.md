# AI Agent Workflow Guide

## Purpose
`AGENTS.md` defines AI agents used in development workflows, their boundaries, and handoff rules.

## Required Project Docs
- `README.md`: high-level project description.
- `CONTEXT.md`: system context for developers and AI tools.
- `ARCHITECTURE.md`: Hubitat app structure and integration blueprint.
- `SECURITY.md`: security policy, credential handling, data access, AI restrictions.
- `STANDARDS.md`: coding and operational standards.
- `DECISIONS.md`: architectural decision record (ADRs).
- `ROADMAP.md`: project development priorities, sequencing, and status.
- `CHANGELOG.md`: release history.

## Workflow Stages
- Context intake: read `README.md`, `CONTEXT.md`, and current `DECISIONS.md`.
- Design: update `ARCHITECTURE.md` and document tradeoffs.
- Implementation: apply changes under `app/` and supporting documentation.
- Verification: run available checks and record Hubitat manual verification evidence when behavior changes.
- Documentation and release: update affected docs and `CHANGELOG.md`.

## Agent Catalog
### Planner Agent
- Purpose: turn requests into decision-complete implementation plans.
- Inputs: user request, `CONTEXT.md`, `ARCHITECTURE.md`, `ROADMAP.md`.
- Outputs: ordered implementation plan with acceptance criteria.
- Allowed actions: analysis, repo inspection, non-mutating checks.
- Disallowed actions: editing files during planning-only tasks.
- Handoff to: Implementer Agent.
- Update triggers: planning workflow or acceptance criteria policy changes.

### Implementer Agent
- Purpose: implement approved plans and keep behavior/docs aligned.
- Inputs: approved plan, codebase state, standards and security policies.
- Outputs: code/doc changes, test results summary, risk notes.
- Allowed actions: edit files, run available build/test/lint commands, and document manual Hubitat verification needs.
- Disallowed actions: bypassing security policy or undocumented behavior changes.
- Handoff to: Reviewer Agent.
- Update triggers: coding workflow, tooling, or execution constraints change.

### Reviewer Agent
- Purpose: detect defects, regressions, and policy violations.
- Inputs: diffs, test evidence, `STANDARDS.md`, `SECURITY.md`.
- Outputs: prioritized findings and required fixes.
- Allowed actions: static review, risk analysis, validation checks.
- Disallowed actions: approving unresolved critical findings.
- Handoff to: Implementer Agent or Docs Agent.
- Update triggers: review gates or severity policy changes.

### Docs Agent
- Purpose: keep required root docs accurate after changes.
- Inputs: merged behavior/design/policy changes.
- Outputs: synchronized updates to affected markdown docs.
- Allowed actions: edit docs, improve cross-links, clarify ownership.
- Disallowed actions: changing product behavior in docs-only tasks.
- Handoff to: Release Agent.
- Update triggers: document ownership or template structure changes.

### Release Agent
- Purpose: finalize release notes and version-facing updates.
- Inputs: merged changes, decisions, and verification outcomes.
- Outputs: `CHANGELOG.md` updates and release summary.
- Allowed actions: compile release deltas and readiness notes.
- Disallowed actions: shipping without required validation evidence.
- Handoff to: none.
- Update triggers: release process or changelog policy changes.

## Coordination Rules
- Do not skip handoffs when ownership changes between planning, implementation, review, docs, and release.
- Resolve conflicts using source-of-truth docs in this order: `SECURITY.md`, `STANDARDS.md`, `ARCHITECTURE.md`, `CONTEXT.md`, `README.md`.
- For plain `html/css/js` interfaces, prefer Bootstrap before custom CSS.
- PowerShell is acceptable and preferred on Windows automation paths.

## Pull Request Policy for Agent Workflows
- Include summary, rationale, and verification evidence, including Hubitat manual verification notes as needed.
- Update `AGENTS.md` when agent roles, allowed actions, disallowed actions, handoffs, or update triggers change.
Companion docs update rules when scope changes:
- `CONTEXT.md` for assumptions, stakeholders, or guardrails.
- `ARCHITECTURE.md` for Hubitat app structure, integration strategy, or runtime behavior.
- `STANDARDS.md` for conventions, quality gates, or ops procedures.
- `SECURITY.md` for credential, data-access, or AI safety policy changes.
- `DECISIONS.md` for architecture changes, major tradeoffs, and superseded design choices.
- `ROADMAP.md` for priority, sequencing, ownership, or milestone status changes.
