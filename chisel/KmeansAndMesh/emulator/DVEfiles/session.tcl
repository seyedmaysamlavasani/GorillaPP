# Begin_DVE_Session_Save_Info
# DVE full session
# Saved on Thu Oct 10 21:25:14 2013
# Designs open: 1
#   Sim: /proj/maysam/magilla/Gorilla++/chisel/KmeansAndMesh/emulator/simv
# Toplevel windows open: 1
# 	TopLevel.1
#   Source.1: test.Offloaded.gOffloadedComponent.mainComp
#   Wave.1: 8 signals
#   Group count = 1
#   Group Group1 signal count = 8
# End_DVE_Session_Save_Info

# DVE version: D-2009.12
# DVE build date: Nov 13 2009 22:21:45


#<Session mode="Full" path="/proj/maysam/magilla/Gorilla++/chisel/KmeansAndMesh/emulator/DVEfiles/session.tcl" type="Debug">

gui_set_loading_session_type Post
gui_continuetime_set

# Close design
if { [gui_sim_state -check active] } {
    gui_sim_terminate
    gui_sim_wait terminated
}
gui_close_db -all
gui_expr_clear_all

# Close all windows
gui_close_window -type Console
gui_close_window -type Wave
gui_close_window -type Source
gui_close_window -type Schematic
gui_close_window -type Data
gui_close_window -type DriverLoad
gui_close_window -type List
gui_close_window -type Memory
gui_close_window -type HSPane
gui_close_window -type DLPane
gui_close_window -type Assertion
gui_close_window -type CovHier
gui_close_window -type CoverageTable
gui_close_window -type CoverageMap
gui_close_window -type CovDensity
gui_close_window -type CovDetail
gui_close_window -type Local
gui_close_window -type Watch
gui_close_window -type Grading
gui_close_window -type Group
gui_close_window -type Transaction



# Application preferences
gui_set_pref_value -key app_default_font -value {Helvetica,10,-1,5,50,0,0,0,0,0}
gui_src_preferences -tabstop 8 -maxbits 24 -windownumber 1
#<WindowLayout>

# DVE Topleve session: 


# Create and position top-level windows :TopLevel.1

if {![gui_exist_window -window TopLevel.1]} {
    set TopLevel.1 [ gui_create_window -type TopLevel \
       -icon $::env(DVE)/auxx/gui/images/toolbars/dvewin.xpm] 
} else { 
    set TopLevel.1 TopLevel.1
}
gui_show_window -window ${TopLevel.1} -show_state normal -rect {{226 141} {1478 974}}

# ToolBar settings
gui_set_toolbar_attributes -toolbar {TimeOperations} -dock_state top
gui_set_toolbar_attributes -toolbar {TimeOperations} -offset 0
gui_show_toolbar -toolbar {TimeOperations}
gui_set_toolbar_attributes -toolbar {&File} -dock_state top
gui_set_toolbar_attributes -toolbar {&File} -offset 0
gui_show_toolbar -toolbar {&File}
gui_set_toolbar_attributes -toolbar {&Edit} -dock_state top
gui_set_toolbar_attributes -toolbar {&Edit} -offset 0
gui_show_toolbar -toolbar {&Edit}
gui_set_toolbar_attributes -toolbar {Simulator} -dock_state top
gui_set_toolbar_attributes -toolbar {Simulator} -offset 0
gui_show_toolbar -toolbar {Simulator}
gui_set_toolbar_attributes -toolbar {Signal} -dock_state top
gui_set_toolbar_attributes -toolbar {Signal} -offset 0
gui_show_toolbar -toolbar {Signal}
gui_set_toolbar_attributes -toolbar {&Scope} -dock_state top
gui_set_toolbar_attributes -toolbar {&Scope} -offset 0
gui_show_toolbar -toolbar {&Scope}
gui_set_toolbar_attributes -toolbar {&Trace} -dock_state top
gui_set_toolbar_attributes -toolbar {&Trace} -offset 0
gui_show_toolbar -toolbar {&Trace}
gui_set_toolbar_attributes -toolbar {BackTrace} -dock_state top
gui_set_toolbar_attributes -toolbar {BackTrace} -offset 0
gui_show_toolbar -toolbar {BackTrace}
gui_set_toolbar_attributes -toolbar {&Window} -dock_state top
gui_set_toolbar_attributes -toolbar {&Window} -offset 0
gui_show_toolbar -toolbar {&Window}
gui_set_toolbar_attributes -toolbar {Zoom} -dock_state top
gui_set_toolbar_attributes -toolbar {Zoom} -offset 0
gui_show_toolbar -toolbar {Zoom}
gui_set_toolbar_attributes -toolbar {Zoom And Pan History} -dock_state top
gui_set_toolbar_attributes -toolbar {Zoom And Pan History} -offset 0
gui_show_toolbar -toolbar {Zoom And Pan History}

# End ToolBar settings

# Docked window settings
set HSPane.1 [gui_create_window -type HSPane -parent ${TopLevel.1} -dock_state left -dock_on_new_line true -dock_extent 320]
set Hier.1 [gui_share_window -id ${HSPane.1} -type Hier]
gui_set_window_pref_key -window ${HSPane.1} -key dock_width -value_type integer -value 320
gui_set_window_pref_key -window ${HSPane.1} -key dock_height -value_type integer -value 720
gui_set_window_pref_key -window ${HSPane.1} -key dock_offset -value_type integer -value 0
gui_update_layout -id ${HSPane.1} {{left 0} {top 0} {width 319} {height 734} {show_state normal} {dock_state left} {dock_on_new_line true} {child_hier_colhier 245} {child_hier_coltype 100} {child_hier_col1 0} {child_hier_col2 1}}
#### Start - Readjusting docked view's offset / size
set dockAreaList { top left right bottom }
foreach dockArea $dockAreaList {
  set viewList [gui_ekki_get_window_ids -active_parent -dock_area $dockArea]
  foreach view $viewList {
      if {[lsearch -exact [gui_get_window_pref_keys -window $view] dock_width] != -1} {
        set dockWidth [gui_get_window_pref_value -window $view -key dock_width]
        set dockHeight [gui_get_window_pref_value -window $view -key dock_height]
        set offset [gui_get_window_pref_value -window $view -key dock_offset]
        if { [string equal "top" $dockArea] || [string equal "bottom" $dockArea]} {
          gui_set_window_attributes -window $view -dock_offset $offset -width $dockWidth
        } else {
          gui_set_window_attributes -window $view -dock_offset $offset -height $dockHeight
        }
      }
  }
}
#### End - Readjusting docked view's offset / size
gui_sync_global -id ${TopLevel.1} -option true

# MDI window settings
set Console.1 [gui_create_window -type {Console}  -parent ${TopLevel.1}]
gui_show_window -window ${Console.1} -show_state maximized
gui_update_layout -id ${Console.1} {{show_state maximized} {dock_state undocked} {dock_on_new_line false}}
set DLPane.1 [gui_create_window -type {DLPane}  -parent ${TopLevel.1}]
if {[gui_get_shared_view -id ${DLPane.1} -type Data] == {}} {
        set Data.1 [gui_share_window -id ${DLPane.1} -type Data]
} else {
        set Data.1  [gui_get_shared_view -id ${DLPane.1} -type Data]
}

gui_show_window -window ${DLPane.1} -show_state maximized
gui_update_layout -id ${DLPane.1} {{show_state maximized} {dock_state undocked} {dock_on_new_line false} {child_data_colvariable 396} {child_data_colvalue 255} {child_data_coltype 262} {child_data_col1 0} {child_data_col2 1} {child_data_col3 2} {dataShowMode detail} {max_item_length 50}}
set Source.1 [gui_create_window -type {Source}  -parent ${TopLevel.1}]
gui_show_window -window ${Source.1} -show_state maximized
gui_update_layout -id ${Source.1} {{show_state maximized} {dock_state undocked} {dock_on_new_line false}}
set Wave.1 [gui_create_window -type {Wave}  -parent ${TopLevel.1}]
gui_show_window -window ${Wave.1} -show_state maximized
gui_update_layout -id ${Wave.1} {{show_state maximized} {dock_state undocked} {dock_on_new_line false} {child_wave_left 417} {child_wave_right 510} {child_wave_colname 275} {child_wave_colvalue 137} {child_wave_col1 0} {child_wave_col2 1}}

# End MDI window settings

gui_set_env TOPLEVELS::TARGET_FRAME(Source) ${TopLevel.1}
gui_set_env TOPLEVELS::TARGET_FRAME(Schematic) ${TopLevel.1}
gui_set_env TOPLEVELS::TARGET_FRAME(PathSchematic) ${TopLevel.1}
gui_set_env TOPLEVELS::TARGET_FRAME(Wave) ${TopLevel.1}
gui_set_env TOPLEVELS::TARGET_FRAME(List) none
gui_set_env TOPLEVELS::TARGET_FRAME(Memory) ${TopLevel.1}
gui_set_env TOPLEVELS::TARGET_FRAME(DriverLoad) none
gui_update_statusbar_target_frame ${TopLevel.1}

#</WindowLayout>

#<Database>

# DVE Open design session: 

if { [llength [lindex [gui_get_db -design Sim] 0]] == 0 } {
gui_set_env SIMSETUP::SIMARGS {{}}
gui_set_env SIMSETUP::SIMEXE {./simv}
gui_set_env SIMSETUP::ALLOW_POLL {0}
if { ![gui_is_db_opened -db {/proj/maysam/magilla/Gorilla++/chisel/KmeansAndMesh/emulator/simv}] } {
gui_sim_run Ucli -exe simv -args {-ucligui } -dir /proj/maysam/magilla/Gorilla++/chisel/KmeansAndMesh/emulator -nosource
}
}
if { ![gui_sim_state -check active] } {error "Simulator did not start correctly" error}
gui_set_precision 1s
gui_set_time_units 1s
#</Database>

# DVE Global setting session: 


# Global: Breakpoints

# Global: Bus

# Global: Expressions

# Global: Signal Time Shift

# Global: Signal Compare

# Global: Signal Groups

set {Group1} {Group1}
gui_sg_create ${Group1}
gui_sg_addsignal -group ${Group1} { {test.Offloaded.gOffloadedComponent.mainComp.clk} {test.Offloaded.gOffloadedComponent.mainComp.reset} {test.Offloaded.gOffloadedComponent.mainComp.io_in_ready} {test.Offloaded.gOffloadedComponent.mainComp.io_in_valid} {test.Offloaded.gOffloadedComponent.mainComp.io_in_bits} {test.Offloaded.gOffloadedComponent.mainComp.io_out_ready} {test.Offloaded.gOffloadedComponent.mainComp.io_out_valid} {test.Offloaded.gOffloadedComponent.mainComp.io_out_bits} }

# Global: Highlighting

# Post database loading setting...

# Restore C1 time
gui_set_time -C1_only 20950700



# Save global setting...

# Wave/List view global setting
gui_cov_show_value -switch false

# Close all empty TopLevel windows
foreach __top [gui_ekki_get_window_ids -type TopLevel] {
    if { [llength [gui_ekki_get_window_ids -parent $__top]] == 0} {
        gui_close_window -window $__top
    }
}
gui_set_loading_session_type noSession
# DVE View/pane content session: 


# Hier 'Hier.1'
gui_list_set_filter -id ${Hier.1} -list { {Package 1} {All 1} {Process 1} {UnnamedProcess 1} {Function 1} {Block 1} {OVA Unit 1} {LeafScCell 1} {LeafVlgCell 1} {Interface 1} {LeafVhdCell 1} {NamedBlock 1} {Task 1} {DollarUnit 1} {VlgPackage 1} {ClassDef 1} }
gui_list_set_filter -id ${Hier.1} -text {*}
gui_hier_list_init -id ${Hier.1}
gui_change_design -id ${Hier.1} -design Sim
catch {gui_list_expand -id ${Hier.1} test}
catch {gui_list_expand -id ${Hier.1} test.Offloaded}
catch {gui_list_expand -id ${Hier.1} test.Offloaded.gOffloadedComponent}
catch {gui_list_select -id ${Hier.1} {test.Offloaded.gOffloadedComponent.offComp}}
gui_view_scroll -id ${Hier.1} -vertical -set 0
gui_view_scroll -id ${Hier.1} -horizontal -set 0

# Data 'Data.1'
gui_list_set_filter -id ${Data.1} -list { {Buffer 1} {Input 1} {Others 1} {Linkage 1} {Output 1} {Parameter 1} {All 1} {Aggregate 1} {Event 1} {Assertion 1} {Constant 1} {Interface 1} {Signal 1} {$unit 1} {Inout 1} {Variable 1} }
gui_list_set_filter -id ${Data.1} -text {*Mux*}
gui_list_show_data -id ${Data.1} {test.Offloaded.gOffloadedComponent.offComp}
gui_view_scroll -id ${Data.1} -vertical -set 0
gui_view_scroll -id ${Data.1} -horizontal -set 0
gui_view_scroll -id ${Hier.1} -vertical -set 0
gui_view_scroll -id ${Hier.1} -horizontal -set 0

# Source 'Source.1'
gui_src_value_annotate -id ${Source.1} -switch false
gui_set_env TOGGLE::VALUEANNOTATE 0
gui_open_source -id ${Source.1}  -replace -active test.Offloaded.gOffloadedComponent.mainComp /proj/maysam/magilla/Gorilla++/chisel/KmeansAndMesh/emulator/Offloaded.v
gui_view_scroll -id ${Source.1} -vertical -set 0
gui_src_set_reusable -id ${Source.1}

# View 'Wave.1'
gui_wv_sync -id ${Wave.1} -switch false
set groupExD [gui_get_pref_value -category Wave -key exclusiveSG]
gui_set_pref_value -category Wave -key exclusiveSG -value {false}
set origWaveHeight [gui_get_pref_value -category Wave -key waveRowHeight]
gui_list_set_height -id Wave -height 25
set origGroupCreationState [gui_list_create_group_when_add -wave]
gui_list_create_group_when_add -wave -disable
gui_marker_set_ref -id ${Wave.1}  C1
gui_wv_zoom_timerange -id ${Wave.1} 20896531 20980027
gui_list_set_filter -id ${Wave.1} -list { {Buffer 1} {Input 1} {Others 1} {Linkage 1} {Output 1} {Parameter 1} {All 1} {Aggregate 1} {Event 1} {Assertion 1} {Constant 1} {Interface 1} {Signal 1} {$unit 1} {Inout 1} {Variable 1} }
gui_list_set_filter -id ${Wave.1} -text {*}
gui_list_add_group -id ${Wave.1} -after {New Group} {{Group1}}
gui_list_select -id ${Wave.1} {test.Offloaded.gOffloadedComponent.mainComp.io_in_valid }
gui_list_set_insertion_bar  -id ${Wave.1} -group {Group1}  -position in
gui_seek_criteria -id ${Wave.1} {Any Edge}



gui_set_env TOGGLE::DEFAULT_WAVE_WINDOW ${Wave.1}
gui_set_pref_value -category Wave -key exclusiveSG -value $groupExD
gui_list_set_height -id Wave -height $origWaveHeight
if {$origGroupCreationState} {
	gui_list_create_group_when_add -wave -enable
}
if { $groupExD } {
 gui_msg_report -code DVWW028
}
gui_marker_move -id ${Wave.1} {C1} 20950700
gui_view_scroll -id ${Wave.1} -vertical -set 0

# DVE Active view and window setting: 

gui_set_active_window -window ${DLPane.1}
gui_set_active_window -window ${DLPane.1}
# Restore toplevel window zorder
# The toplevel window could be closed if it has no view/pane
if {[gui_exist_window -window ${TopLevel.1}]} {
	gui_set_active_window -window ${TopLevel.1} }
#</Session>

