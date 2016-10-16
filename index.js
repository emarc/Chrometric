
const electron = require('electron')
const app = electron.app
const BrowserWindow = electron.BrowserWindow

const {ipcMain} = require('electron')
/*
const path = require('path')
const fs = require('fs')
const directory = require('./vaadin-directory.js')
*/

let win;

function start() {

    win = new BrowserWindow({ width: 800, height: 600 });

    // win.loadURL(`file://${__dirname}/browser.html`);
win.loadURL(`file://${__dirname}/ui/index.html`);

    // Open the DevTools.
    win.webContents.openDevTools();

    // Closing window
    win.on('closed', () => {
        win = null;
    });
}

app.on('ready', start);

// Quitting
app.on('window-all-closed', () => {
    // On OS X it is common for applications and their menu bar
    // to stay active until the user quits explicitly with Cmd + Q
    if (process.platform !== 'darwin') {
        app.quit();
    }
});

app.on('activate', () => {
    // On OS X it's common to re-create a window in the app when the
    // dock icon is clicked and there are no other windows open.
    if (win === null) {
        createWindow();
    }
});

// IPC services
/*
ipcMain.on('getPassword', (event, arg) => {
  event.returnValue = password;
});
*/