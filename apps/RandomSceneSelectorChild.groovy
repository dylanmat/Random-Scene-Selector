definition(
    name: "Random Scene Selector Child",
    namespace: "randomsceneselector",
    parent: "randomsceneselector:Random Scene Selector",
    author: "Random Scene Selector",
    description: "Child app that runs random scene selection logic.",
    category: "Convenience",
    singleInstance: false,
    installOnOpen: true,
    version: "0.1.0"
)

preferences {
    page(name: "mainPage", title: "Random Scene Selector Child", install: true, uninstall: true) {
        section("Configuration") {
            paragraph("Configure your random scene behavior here.")
        }
    }
}
