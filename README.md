# Xaero PointShare
Simple fabric mod to changes xaero's minimap waypoint sharing functionality

## Feautures:
- When sharing a waypoint, it no longer sends it in chat, but just copies
- If in the start of message is `x%s:`, it will show waypoint only to yourself  
Example: `x%self:xaero-waypoint:1:1:9:-60:13:2:false:0:Internal-overworld`
- If waypoint starts with `x%o:`: it will send a waypoint to all players, like in base xaero's minimap

## Why?
This mod is made to share a waypoints between people outside of minecraft(e.g discord)  
I have a channel where we post all our waypoints like:
> Farm of something:
> ```
> x%s:xaero-waypoint:Farm of something:F:0:0:0:0:false:0:Internal-overworld
> ```
and you can just copypaste it into chat to add a waypoint