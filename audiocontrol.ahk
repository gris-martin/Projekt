OnMessage(0x404, "AHK_NOTIFYICON")

; Default output method: Headset
global PlaybackDevice := "Headset"
SetDevice(7) 

; Change tooltip
Menu, Tray, Tip, Change the Sound Output

; If Ctrl+Alt+A is pressed:
^!a::
{
	SwitchDevice()
}

; If tray icon is clicked
AHK_NOTIFYICON(wParam, lParam) 
{
	if (lParam = 0x202 or lParam = 0x203)
	{
		SwitchDevice()
	}
}

; Switches between Speakers and Headset
SwitchDevice()
{
	if (global PlaybackDevice = "Speakers")
	{
		global PlaybackDevice := "Headset"
		IconNumber = 7
	}
	else
	{
		global PlaybackDevice := "Speakers"
		IconNumber = 2
	}
	SetDevice(IconNumber)
	return
}

; Sets the device to whatever PlaybackDevice contains. Changes the tray icon to IconNumber
SetDevice(IconNumber)
{
	Menu, Tray, Icon, Mmres.dll, %IconNumber%
	Run, %comspec% /c ""C:\Program Files\NirCmd\NIRCMDC" setdefaultsounddevice %PlaybackDevice% 1" , , hide
	Run, %comspec% /c ""C:\Program Files\NirCmd\NIRCMDC" setdefaultsounddevice %PlaybackDevice% 2" , , hide
	return
}