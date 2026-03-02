definition(
    name: 'Random Scene Selector',
    namespace: 'dylanm.rss',
    author: 'dylanm',
    description: 'Create per-room random scene selector child apps. v0.1.1',
    category: 'Convenience',
    iconUrl: '',
    iconX2Url: '',
    iconX3Url: '',
    importUrl: '',
    singleInstance: true
)

preferences {
    page(name: 'mainPage', title: 'Random Scene Selector', install: true, uninstall: true) {
        section('Selectors') {
            app(name: 'childSelectors', appName: 'Random Scene Selector Child', namespace: 'dylanm.rss.child', title: 'Add a Random Scene Selector', multiple: true)
        }
    }
}

def installed() { initialize() }
def updated() { unsubscribe(); initialize() }
def initialize() { }
