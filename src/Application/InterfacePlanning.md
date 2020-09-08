# Interfaces
 - Each "Application" can only have one pace opened (Smaller "window" when no pace is opened), however you can open paces in additional windows
 
 
 ##Single-Window Layout
 ### Tab Layout
 Idea could be to use different tabs, such as "Teams", "Divisions". Thing is to self-isolate the tab contents such that in the future I could do a UI overhaul and have a modifiable interface.
  - **Teams** List of all teams. Double Clicking on a team opens up a window to edit the tables. Buttons on the top bar to create teams, streamline data updating, also has statistics for last one out, last one in, etc etc. 
      - **Team Editor** Two separate modes: When opened up to edit a team, and when opened up to create a new team
      - **Streamline Updater** Window that has several options: Put in a team, then hit enter and depending on what you select, it'll redirect your cursor, so you can immediately type the information, such as start time or finish time. 
  - **Division Tab** includes the list of divisions, using a custom table (separators to make it look nice), so I can include the goal-editor built in. Entering nothing into the field makes the goal "null". Set it up where each row is a special class that adds all the nodes, so adding or removing them can be easier.