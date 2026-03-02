import groovy.transform.Field

@Field static final String APP_VERSION = '0.1.0'
@Field static final String ACTIVATOR_SUFFIX = 'Scene Activator'

definition(
    name: 'Random Scene Selector Child',
    namespace: 'dylanm.rss.child',
    author: 'dylanm',
    description: 'Creates a random Hue scene activator button.',
    category: 'Convenience',
    iconUrl: '',
    iconX2Url: '',
    iconX3Url: ''
)

preferences {
    page(name: 'mainPage', title: 'Random Scene Selector', install: true, uninstall: true)
}

def mainPage() {
    dynamicPage(name: 'mainPage', title: 'Random Scene Selector', install: true, uninstall: true) {
        section('Selector Configuration') {
            input 'selectorName', 'text', title: 'Selector name', required: true
            input 'overrideSwitches', 'capability.switch', title: 'Override switches to turn on', multiple: true, required: false
            input 'hueMode', 'enum', title: 'Hue activation mode', required: true, options: (1..9).collectEntries { ["${it}": "Mode ${it}"] }, defaultValue: '1'
            input 'scenes', 'capability.actuator', title: 'Hue scene devices', multiple: true, required: true
        }

        section('Version') {
            paragraph "Random Scene Selector Child v${APP_VERSION}"
        }
    }
}

def installed() {
    logInfo('Installed child app')
    initialize()
}

def updated() {
    logInfo('Updated child app')
    unsubscribe()
    unschedule()
    initialize()
}

def uninstalled() {
    def device = getActivatorDevice(false)
    if (device) {
        deleteChildDevice(device.deviceNetworkId)
        logInfo("Deleted activator device ${device.displayName}")
    }
}

def initialize() {
    def device = getActivatorDevice(true)
    if (!device) {
        logError('Failed to initialize activator device')
        return
    }

    subscribe(device, 'pushed', 'activatorPushedHandler')
    logInfo("Ready: ${device.displayName} subscribed to pushed events")
}

def activatorPushedHandler(evt) {
    if (!evt || evt.value != '1') {
        logDebug("Ignoring non-button-1 event value: ${evt?.value}")
        return
    }

    List selectedScenes = (settings?.scenes instanceof List) ? settings.scenes.findAll { it } : (settings?.scenes ? [settings.scenes] : [])
    if (!selectedScenes) {
        logWarn('No scenes configured, nothing to activate')
        return
    }

    List switches = (settings?.overrideSwitches instanceof List) ? settings.overrideSwitches.findAll { it } : (settings?.overrideSwitches ? [settings.overrideSwitches] : [])
    switches.each {
        try {
            it.on()
        } catch (Exception ex) {
            logWarn("Unable to turn on override switch ${it?.displayName ?: 'unknown'}: ${ex.message}")
        }
    }

    def selectedScene = selectedScenes[new Random().nextInt(selectedScenes.size())]
    Integer mode = safeToInt(settings?.hueMode, 1)
    logInfo("Button 1 pressed. mode=${mode}, sceneCount=${selectedScenes.size()}, chosen=${selectedScene?.displayName}, overrideCount=${switches.size()}")
    activateScene(selectedScene, mode)
}

private void activateScene(scene, Integer mode) {
    if (!scene) {
        logWarn('Chosen scene is null, skipping activation')
        return
    }

    try {
        switch (mode) {
            case 1: scene.on(); break
            case 2: scene.push(1); break
            case 3: scene.push(); break
            case 4: scene.activate(); break
            case 5: scene.run(); break
            case 6: scene.trigger(); break
            case 7: scene.sceneOn(); break
            case 8: scene.execute(); break
            case 9: scene.start(); break
            default: scene.on(); break
        }
        logInfo("Activated scene ${scene.displayName} using mode ${mode}")
    } catch (Exception ex) {
        logError("Failed activating scene ${scene?.displayName ?: 'unknown'} with mode ${mode}: ${ex.message}")
    }
}

private getActivatorDevice(Boolean createIfMissing = true) {
    String trimmedName = (settings?.selectorName ?: app?.label ?: 'Random Scene').trim()
    String label = "${trimmedName} ${ACTIVATOR_SUFFIX}".trim()
    String dni = "rss-activator-${app.id}"

    def device = getChildDevice(dni)
    if (device) {
        if (device.label != label) {
            device.setLabel(label)
            logInfo("Updated activator label to ${label}")
        }
        return device
    }

    if (!createIfMissing) {
        return null
    }

    try {
        device = addChildDevice('hubitat', 'Virtual Button', dni, [label: label, isComponent: false, name: label])
        if (device?.hasCommand('setNumberOfButtons')) {
            device.setNumberOfButtons(1)
        }
        logInfo("Created activator device ${label}")
    } catch (Exception ex) {
        logError("Unable to create activator device ${label}: ${ex.message}")
    }

    return device
}

private Integer safeToInt(value, Integer fallback) {
    try {
        return value as Integer
    } catch (Exception ignored) {
        return fallback
    }
}

private void logDebug(String msg) { log.debug "${app?.label ?: app?.name}: ${msg}" }
private void logInfo(String msg) { log.info "${app?.label ?: app?.name}: ${msg}" }
private void logWarn(String msg) { log.warn "${app?.label ?: app?.name}: ${msg}" }
private void logError(String msg) { log.error "${app?.label ?: app?.name}: ${msg}" }
