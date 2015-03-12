pa2sample is a visual c++ application coded in visual studio 6.0 and slightly updated in Visual Studio 2005. 
When running, It presents a simple dialog-based window which contains a menu item "open" for opening a video file, 
a button "Getframe" and a picuture box. When you click the button, a video frame will show in the picture box; 
click again, the next frame will show.  There is also a "Play/Pause" button, should you choose to playback
the file, which will open a separate playback window.  A "Stop" button is also present, which will end playback 
and reset the current video position to the very beginning.

The main functions in this application is "Ongetframe" and "showFrame", both of them in pa2sampleDlg.cpp.
"Ongetframe" calls the functions of class video to get access to video frames. Class video is located in 
video.cpp and video.h, which encapsulates the complicated DirectShow COM interfaces, providing 
easier interfaces to separate users from details of controlling video playback.  

Some of the interfaces provided by video class include:
1.	Open: opens and renders the video file, rejecting audio/MIDI files or unsupported video codecs;  
2.	Play:  sets the private variable "bstopped" to false and plays or pauses the video;
3.	Stop: sets the private variable "bstopped" to true and stops the video;
4.   Restart: mainly sets the "lCurrentposition" to 1. "lCurrentposition" is a counter indexing the current frame.
5.	GetCurrentImage: grabs the current image frame indexed by "lCurrentposition";
6.	GetCurrentPosition: returns the current position variable "lCurrentpositin";
7.	GetDuration: returns the duration.

