

// Place your key bindings in this file to override the defaults
[
    {
        "key": "\\ backspace",
        "command": "editor.action.refactor"
    },
    {
        "key": "shift+enter",
        "command": "editor.action.insertLineAfter",
        "when": "editorTextFocus"
    },
    {
        "key": "shift+alt+up",
        "command": "editor.action.smartSelect.expand",
        "when": "editorTextFocus"
    },{
        "key": "ctrl+shift+enter",
        "command": "macros.addSemicolon",
        "when": "editorTextFocus"
    },
    {
        "key": "ctrl+shift+=",
        "command": "editor.unfoldAll",
        "when": "editorTextFocus && foldingEnabled"
    },
    {
        "key": "ctrl+k ctrl+j",
        "command": "-editor.unfoldAll",
        "when": "editorTextFocus && foldingEnabled"
    },
    {
        "key": "ctrl+shift+-",
        "command": "editor.foldAll",
        "when": "editorTextFocus && foldingEnabled"
    },
    {
        "key": "ctrl+k ctrl+0",
        "command": "-editor.foldAll",
        "when": "editorTextFocus && foldingEnabled"
    },
    {
        "key": "ctrl+shift+f10",
        "command": "python.execInTerminal"
    },
    {
        "key": "alt+left",
        "command": "-workbench.action.terminal.focusPreviousPane",
        "when": "terminalFocus && terminalProcessSupported"
    },
    {
        "key": "alt+left",
        "command": "workbench.action.navigateBack"
    },
    {
        "key": "ctrl+alt+-",
        "command": "-workbench.action.navigateBack"
    },
    {
        "key": "alt+z",
        "command": "pylance.extractVariable"
    },
    {
        "key": "alt+z",
        "command": "-editor.action.toggleWordWrap"
    },
    {
        "key": "alt+z",
        "command": "-workbench.action.terminal.sizeToContentWidth",
        "when": "terminalFocus && terminalIsOpen && terminalProcessSupported"
    },
    {
        "key": "alt+l",
        "command": "cursorWordRight"
    },
    {
        "key": "alt+h",
        "command": "cursorWordLeft",
        "when": "textInputFocus"
    },
    {
        "key": "ctrl+numpad0",
        "command": "workbench.action.focusSideBar"
    },
    {
        "key": "ctrl+numpad1",
        "command": "workbench.action.focusFirstEditorGroup"
    },
    {
        "key": "ctrl+numpad2",
        "command": "workbench.action.focusSecondEditorGroup"
    }
]


