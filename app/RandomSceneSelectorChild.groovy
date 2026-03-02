/**
 *  Random Scene Selector Child
 *  Version: 0.1.0
 */
definition(
    name: 'Random Scene Selector Child',
    namespace: 'dylanm.rss.child',
    parent: 'dylanm.rss:Random Scene Selector (Parent)',
    author: 'dylanm',
    description: 'Child app that randomly activates one configured scene.',
    category: 'Convenience',
    singleInstance: false,
    installOnOpen: true
)

preferences {
    page(name: 'mainPage', title: 'Random Scene Selector Child', install: true, uninstall: true)
}

def mainPage() {
    dynamicPage(name: 'mainPage') {
        section('Configuration') {
            label title: 'Child App Name', required: false
            input 'scenes', 'capability.switch', title: 'Select scene switches', multiple: true, required: false
        }
        section('Actions') {
            paragraph 'Use Run to activate a random configured scene.'
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
    log.debug "${app.label ?: app.name} initialized."
}

def runRandomScene() {
    if (!settings?.scenes) {
        log.warn 'No scenes configured to run.'
        return
    }

    def selected = settings.scenes[new Random().nextInt(settings.scenes.size())]
    log.info "Activating scene: ${selected.displayName}"
    selected.on()
}
