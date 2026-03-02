/**
 *  Random Scene Selector (Parent)
 *  Version: 0.1.0
 */
definition(
    name: 'Random Scene Selector (Parent)',
    namespace: 'dylanm.rss',
    author: 'dylanm',
    description: 'Parent app that hosts Random Scene Selector child apps.',
    category: 'Convenience',
    singleInstance: true,
    installOnOpen: true
)

preferences {
    page(name: 'mainPage', title: 'Random Scene Selector', install: true, uninstall: true)
}

def mainPage() {
    dynamicPage(name: 'mainPage') {
        section('Child Apps') {
            app(
                name: 'childApps',
                appName: 'Random Scene Selector Child',
                namespace: 'dylanm.rss.child',
                title: 'Add New Random Scene Selector Child',
                multiple: true
            )
        }
    }
}

def installed() {
    initialize()
}

def updated() {
    unsubscribe()
    unschedule()
    initialize()
}

def initialize() {
    log.debug 'Random Scene Selector Parent initialized.'
}
