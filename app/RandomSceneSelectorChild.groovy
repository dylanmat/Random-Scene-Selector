definition(
    name: 'Random Scene Selector Child',
    namespace: 'dylanm.rss.child',
    parent: 'dylanm.rss:Random Scene Selector',
    author: 'dylanm',
    description: 'Creates a scene activator button that chooses a random Hue scene. v0.1.2',
    category: 'Convenience',
    iconUrl: '',
    iconX2Url: '',
    iconX3Url: '',
    importUrl: ''
)

preferences {
    page(name: 'configPage', title: 'Random Scene Selector Child', install: true, uninstall: true) {
        section('Selector settings') {
            input 'selectorName', 'text', title: 'Random Selector Name', required: true
            input 'overrideSwitches', 'capability.switch', title: 'Override Switches', multiple: true, required: false
            input 'hueMode', 'enum', title: 'Hue Scene Mode (1-9)', required: true,
                options: [
                    '1': '1 - Default',
                    '2': '2 - Dynamic palette',
                    '3': '3 - Static',
                    '4': '4 - Dynamic palette, custom duration',
                    '5': '5 - Static, custom duration',
                    '6': '6 - Dynamic palette, custom brightness',
                    '7': '7 - Static, custom brightness',
                    '8': '8 - Dynamic palette, custom duration and brightness',
                    '9': '9 - Static, custom duration and brightness'
                ],
                defaultValue: '1'
            input 'scenes', 'capability.actuator', title: 'Scenes to randomize', multiple: true, required: true
            input 'enableDebug', 'bool', title: 'Enable debug logging', defaultValue: false, required: false
        }
    }
}

def installed() { initialize() }
def updated() { unsubscribe(); initialize() }
def uninstalled() { deleteActivator() }

def initialize() {
    updateAppLabel()
    def button = createOrUpdateActivator()
    if (button) subscribe(button, 'pushed', handlePushed)
}

def handlePushed(evt) {
    def pickedScene = pickRandomScene()
    if (!pickedScene) return logMsg('warn', 'No scenes configured; nothing to activate.')
    overrideSwitches?.findAll { it }?.each { it.on() }
    activateScene(pickedScene)
    state.lastSelectedSceneId = pickedScene.id
    state.lastActivatedAt = now()
    logMsg('info', "Selector '${safeName()}' activated '${pickedScene.displayName}' using mode ${selectedMode()}.")
}

def pickRandomScene() {
    def choices = scenes?.findAll { it }
    choices ? choices[new Random().nextInt(choices.size())] : null
}

def activateScene(sceneDevice) {
    Integer mode = selectedMode()
    try {
        if (sceneDevice?.hasCommand('sceneOn')) sceneDevice.sceneOn(mode)
        else sceneDevice?.on()
    } catch (ignored) {
        logMsg('warn', "Scene command failed for '${sceneDevice?.displayName}'.")
    }
}

def createOrUpdateActivator() {
    String dni = "rss-activator-${app.id}"
    String label = "${safeName()} Scene Activator"
    def child = getChildDevice(dni) ?: addChildDevice('hubitat', 'Virtual Button', dni, [label: label, name: label, isComponent: false])
    if (child && child.label != label) child.setLabel(label)
    child
}

def deleteActivator() {
    def device = getChildDevice("rss-activator-${app.id}")
    if (device) deleteChildDevice(device.deviceNetworkId)
}

def updateAppLabel() {
    String desired = safeName()
    if (app.label != desired) app.updateLabel(desired)
}

def safeName() { selectorName?.trim() ?: 'Random Selector' }
def selectedMode() { (hueMode ?: '1') as Integer }

def logMsg(level, msg) {
    if (level == 'debug' && !enableDebug) return
    switch (level) {
        case 'error': log.error msg; break
        case 'warn': log.warn msg; break
        case 'info': log.info msg; break
        default: log.debug msg
    }
}
