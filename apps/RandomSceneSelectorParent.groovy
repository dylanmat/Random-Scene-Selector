definition(
    name: "Random Scene Selector",
    namespace: "randomsceneselector",
    author: "Random Scene Selector",
    description: "Parent app for managing random scene selector child instances.",
    category: "Convenience",
    singleInstance: true,
    installOnOpen: true,
    version: "0.1.0"
)

preferences {
    page(name: "mainPage", title: "Random Scene Selector", install: true, uninstall: true) {
        section("Child apps") {
            app(name: "childApps", appName: "Random Scene Selector Child", namespace: "randomsceneselector", title: "Add child app", multiple: true)
        }
    }
}
