import groovy.transform.Field

@Field static final String APP_VERSION = '0.1.0'

definition(
    name: 'Random Scene Selector Parent',
    namespace: 'dylanm.rss.parent',
    author: 'dylanm',
    description: 'Parent manager for Random Scene Selector child apps.',
    category: 'Convenience',
    importUrl: '',
    singleInstance: true,
    installOnOpen: true,
    iconUrl: '',
    iconX2Url: ''
)

preferences {
    page(name: 'mainPage', title: 'Random Scene Selector', install: true, uninstall: true)
}

def mainPage() {
    dynamicPage(name: 'mainPage', title: 'Random Scene Selector', install: true, uninstall: true) {
        section("Create Selector (${childApps?.size() ?: 0})") {
            app(
                name: 'childApps',
                appName: 'Random Scene Selector Child',
                namespace: 'dylanm.rss.child',
                title: 'Add a Random Scene Selector',
                multiple: true
            )
        }

        if (childApps) {
            section('Configured Selectors') {
                childApps.sort { it.label ?: it.name }.each { child ->
                    paragraph "• ${child.label ?: child.name}"
                }
            }
        }

        section('Version') {
            paragraph "Random Scene Selector Parent v${APP_VERSION}"
        }
    }
}

def installed() {
    log.info 'Installed parent app'
    initialize()
}

def updated() {
    log.info 'Updated parent app'
    unsubscribe()
    unschedule()
    initialize()
}

def uninstalled() {
    childApps?.each { deleteChildApp(it.id) }
}

def initialize() {
    log.debug "Initializing parent with ${childApps?.size() ?: 0} child app(s)"
}
