soundexample is a visual c++ application coded in visual studio 6.0. when running, it presents a simple 
dialog-based window which contains a button "sound". When clicking this button, it plays a "chord"
of sound.

The function "OnSound" is used to play a sin wave sound with frequency 440Hz, loudness 0.5,
sampling rate 8000Hz, and 500 samples. It calls functions provided in class Sound to sound this wave. 
NOTE: Even with a loudness/intensity of 0.5, it may be inaudible at a low speaker volume.

Class sound is located in 
sound.cpp and sound.h, which encapsulates the mmsystem API, providing 
easier interfaces to users.  

Note:
To access these functions, include the header file:

#include <mmsystem.h>

and add the multimedia library, winmm.lib, to the linker:

Project -> Settings -> Link tab -> Object/library modules: winmm.lib


 
 