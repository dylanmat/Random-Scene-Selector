# DECISIONS

## Purpose
Track architectural decisions, including why they were made and their long-term impact.

## ADR Template
Use this format for each new decision:
- Date:
- ID: ADR-000
- Status: Proposed | Accepted | Superseded | Deprecated
- Context:
- Decision:
- Consequences:
- Alternatives Considered:
- Supersedes/Superseded By:

## ADR Entries

### ADR-001 - Parent and Child Namespace Contract
- Date: 2026-03-01
- Status: Accepted
- Context: Hubitat parent/child app registration depends on stable app names and namespaces.
- Decision: Use `dylanm.rss` for the parent app and `dylanm.rss.child` for the child app.
- Consequences: Future changes must preserve these namespaces unless a migration plan is documented.
- Alternatives Considered: Adding `.parent` to the parent namespace was considered and rejected.
- Supersedes/Superseded By: None.

### ADR-002 - Parent-Only Child App
- Date: 2026-03-02
- Status: Accepted
- Context: Child app instances should be created and managed from the parent app, not installed directly by users.
- Decision: Keep the child app non-standalone with `parent: 'dylanm.rss:Random Scene Selector'`.
- Consequences: Installation instructions must tell users to install the parent app only from Add User App.
- Alternatives Considered: Standalone child installation was rejected to keep selector lifecycle centralized.
- Supersedes/Superseded By: None.

### ADR-003 - Hubitat Metadata Ordering
- Date: 2026-03-02
- Status: Accepted
- Context: Child creation failed when Hubitat did not reliably register parent/child metadata.
- Decision: Keep `definition(...)` as the first declaration in parent and child app files.
- Consequences: Future edits must not move imports, constants, comments, or helper declarations ahead of `definition(...)` if that risks registration behavior.
- Alternatives Considered: Leaving metadata lower in the file was rejected after the parent linkage failure.
- Supersedes/Superseded By: None.

### ADR-004 - Virtual Button Scene Activator
- Date: 2026-03-02
- Status: Accepted
- Context: Each selector needs a simple device that can be used from dashboards and automations.
- Decision: Each child app creates one built-in Hubitat `Virtual Button` device named `<Random Selector Name> Scene Activator`.
- Consequences: Button 1 is the activation interface; additional button behavior requires a future feature decision.
- Alternatives Considered: No custom driver is used for the current implementation.
- Supersedes/Superseded By: None.
