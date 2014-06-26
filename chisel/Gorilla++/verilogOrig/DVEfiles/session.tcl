# Begin_DVE_Session_Save_Info
# DVE full session
# Saved on Wed Nov 6 14:21:48 2013
# Designs open: 1
#   Sim: /proj/maysam/magilla/Gorilla++/chisel/Gorilla++/verilog/simv
# Toplevel windows open: 1
# 	TopLevel.1
#   Source.1: test
#   Wave.2: 136 signals
#   Wave.1: 304 signals
#   Group count = 2
#   Group mainComp signal count = 304
#   Group offComp signal count = 136
# End_DVE_Session_Save_Info

# DVE version: D-2009.12
# DVE build date: Nov 13 2009 22:21:45


#<Session mode="Full" path="/proj/maysam/magilla/Gorilla++/chisel/Gorilla++/verilog/DVEfiles/session.tcl" type="Debug">

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
gui_show_window -window ${TopLevel.1} -show_state normal -rect {{-12 54} {1238 885}}

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
set HSPane.1 [gui_create_window -type HSPane -parent ${TopLevel.1} -dock_state left -dock_on_new_line true -dock_extent 319]
set Hier.1 [gui_share_window -id ${HSPane.1} -type Hier]
gui_set_window_pref_key -window ${HSPane.1} -key dock_width -value_type integer -value 319
gui_set_window_pref_key -window ${HSPane.1} -key dock_height -value_type integer -value 734
gui_set_window_pref_key -window ${HSPane.1} -key dock_offset -value_type integer -value 0
gui_update_layout -id ${HSPane.1} {{left 0} {top 0} {width 318} {height 736} {show_state normal} {dock_state left} {dock_on_new_line true} {child_hier_colhier 277} {child_hier_coltype 100} {child_hier_col1 0} {child_hier_col2 1}}
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
gui_update_layout -id ${DLPane.1} {{show_state maximized} {dock_state undocked} {dock_on_new_line false} {child_data_colvariable 401} {child_data_colvalue 260} {child_data_coltype 267} {child_data_col1 0} {child_data_col2 1} {child_data_col3 2} {dataShowMode detail} {max_item_length 50}}
set Source.1 [gui_create_window -type {Source}  -parent ${TopLevel.1}]
gui_show_window -window ${Source.1} -show_state maximized
gui_update_layout -id ${Source.1} {{show_state maximized} {dock_state undocked} {dock_on_new_line false}}
set Wave.1 [gui_create_window -type {Wave}  -parent ${TopLevel.1}]
gui_show_window -window ${Wave.1} -show_state maximized
gui_update_layout -id ${Wave.1} {{show_state maximized} {dock_state undocked} {dock_on_new_line false} {child_wave_left 417} {child_wave_right 509} {child_wave_colname 275} {child_wave_colvalue 137} {child_wave_col1 0} {child_wave_col2 1}}
set Wave.2 [gui_create_window -type {Wave}  -parent ${TopLevel.1}]
gui_show_window -window ${Wave.2} -show_state maximized
gui_update_layout -id ${Wave.2} {{show_state maximized} {dock_state undocked} {dock_on_new_line false} {child_wave_left 417} {child_wave_right 509} {child_wave_colname 275} {child_wave_colvalue 137} {child_wave_col1 0} {child_wave_col2 1}}

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
gui_set_env SIMSETUP::SIMARGS {{-ucligui }}
gui_set_env SIMSETUP::SIMEXE {/proj/maysam/magilla/Gorilla++/chisel/Gorilla++/verilog/simv}
gui_set_env SIMSETUP::ALLOW_POLL {0}
if { ![gui_is_db_opened -db {/proj/maysam/magilla/Gorilla++/chisel/Gorilla++/verilog/simv}] } {
gui_sim_run Ucli -exe simv -args {-ucligui } -dir /proj/maysam/magilla/Gorilla++/chisel/Gorilla++/verilog -nosource
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
gui_load_child_values {test.Top.generatedTop.source.sink.offComp}

set {mainComp} {mainComp}
gui_sg_create ${mainComp}
gui_sg_addsignal -group ${mainComp} { {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.memPortHadRequest} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T100} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T101} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T102} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T70} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T103} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T71} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T104} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T72} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T73} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T105} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T74} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T106} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T75} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T107} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T76} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T108} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T77} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T109} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.linkIndex} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T78} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T79} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.inputReg_startPageId} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.mem_ready_received} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T110} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T111} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T80} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T112} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T81} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T113} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T82} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T114} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T83} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T115} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T84} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T116} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T85} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T117} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T86} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T118} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.mainOff_rankCalc_req_ready} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T87} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T119} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T88} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T89} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.mainOff_mem_req_bits_rw} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.mainOff_rankCalc_req_bits_fanoutDegree} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.io_in_bits_length} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.io_pcIn_bits_pcValue} }
gui_sg_addsignal -group ${mainComp} { {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T120} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T121} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T90} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T122} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T91} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T123} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T92} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T124} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T93} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T125} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T94} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T126} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T95} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T127} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T96} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T128} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T97} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T129} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T98} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.rankCalc_valid_received} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T99} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.rankCalcPort_req_ready} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.mainOff_rankCalc_req_bits_rank} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.mainOff_rankCalc_rep_valid} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.mainOff_mem_req_ready} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.io_out_valid} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.inputReg_done} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T130} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T131} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.memPort_req_ready} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T132} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T133} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T134} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T135} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T136} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T137} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T138} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T139} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.rankCalcPort_rep_valid} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.io_pcOut_valid} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.outputReg_done} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.mainOff_mem_rep_valid} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.io_pcOut_bits_moduleId} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T140} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.mainOff_mem_req_bits_cached} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T141} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T142} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T143} }
gui_sg_addsignal -group ${mainComp} { {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T144} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.mainOff_rankCalc_req_bits_damping} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T145} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T146} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T147} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T148} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T149} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.io_in_ready} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.memPort_rep_valid} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.mem_valid_received} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.reset} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T150} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T151} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T152} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T153} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.io_pcIn_bits_portId} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.io_pcIn_bits_pcType} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T154} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T155} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.SubState} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T156} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T157} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T158} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T159} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.io_in_bits_done} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.AllOffloadsReady} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.io_pcIn_bits_request} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.io_in_bits_startPageId} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.mainOff_mem_req_bits_data} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.io_pcIn_bits_moduleId} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.mainOff_mem_req_bits_size} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T200} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T201} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T202} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T203} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T160} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T204} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T161} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T205} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T162} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T206} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T163} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T207} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T164} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T165} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T209} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T166} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T167} }
gui_sg_addsignal -group ${mainComp} { {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.io_pcOut_bits_portId} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.io_pcOut_bits_pcType} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T168} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T169} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T10} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T11} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.page_fanoutDegree} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T13} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T14} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T15} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T17} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T18} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T210} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T19} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T211} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T170} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T214} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T171} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T215} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T172} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T216} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T173} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.io_pcOut_bits_pcValue} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T174} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T218} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T175} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.EmitReturnState} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T219} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T176} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T177} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T178} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T179} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.io_out_bits_pageId} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.R12} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.R16} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T21} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T22} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T23} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T24} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T25} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T26} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T27} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T28} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T220} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T29} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T221} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T222} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.inputReg_length} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T223} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T180} }
gui_sg_addsignal -group ${mainComp} { {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.R0} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T224} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T181} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T225} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T182} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T183} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T227} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T184} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.mainOff_rankCalc_req_valid} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T228} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T185} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T229} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T186} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T187} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T188} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.mainOff_rankCalc_rep_bits_out} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.R8} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T189} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.R20} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.io_out_bits_done} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.mainOff_rankCalc_rep_ready} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.io_out_ready} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T30} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T31} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.mainOff_mem_rep_bits_data} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T32} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T33} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T34} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T35} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T36} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.io_pcIn_valid} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T37} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T38} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T39} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T231} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T232} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.fanoutDegree} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T233} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T190} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T234} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T191} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T235} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T192} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.rankCalcPort_rep_ready} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T236} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T193} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T237} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T194} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T238} }
gui_sg_addsignal -group ${mainComp} { {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T195} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T239} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T196} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.rankCalcPort_req_valid} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T197} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T198} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T199} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.clk} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.mainOff_mem_rep_ready} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T40} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T41} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T42} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.mainOff_mem_req_valid} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T43} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T44} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T45} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T46} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T47} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T48} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T240} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T49} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.memPort_req_valid} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.io_out_bits_rankUpdate} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T1} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T2} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T3} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T4} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T5} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.memPort_rep_ready} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T6} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T7} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T9} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T50} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T51} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.pageId} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T52} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T53} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T54} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T55} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T56} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T57} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T58} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T59} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.rankCalc_ready_received} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.io_pcOut_bits_request} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.mainOff_mem_req_bits_addr} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.rankCalcPortHadRequest} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.io_in_valid} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.State} }
gui_sg_addsignal -group ${mainComp} { {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T60} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T61} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T62} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T63} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T64} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T65} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T66} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T67} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T68} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.T69} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.AllOffloadsValid} }
set {offComp} {offComp}
gui_sg_create ${offComp}
gui_sg_addsignal -group ${offComp} { {test.Top.generatedTop.source.sink.offComp.T105} {test.Top.generatedTop.source.sink.offComp.T106} {test.Top.generatedTop.source.sink.offComp.T107} {test.Top.generatedTop.source.sink.offComp.io_pcOut_bits_moduleId} {test.Top.generatedTop.source.sink.offComp.T50} {test.Top.generatedTop.source.sink.offComp.T51} {test.Top.generatedTop.source.sink.offComp.T52} {test.Top.generatedTop.source.sink.offComp.T53} {test.Top.generatedTop.source.sink.offComp.T54} {test.Top.generatedTop.source.sink.offComp.T55} {test.Top.generatedTop.source.sink.offComp.T56} {test.Top.generatedTop.source.sink.offComp.T57} {test.Top.generatedTop.source.sink.offComp.T58} {test.Top.generatedTop.source.sink.offComp.T59} {test.Top.generatedTop.source.sink.offComp.io_in_bits_cached} {test.Top.generatedTop.source.sink.offComp.T60} {test.Top.generatedTop.source.sink.offComp.T61} {test.Top.generatedTop.source.sink.offComp.io_out_valid} {test.Top.generatedTop.source.sink.offComp.T62} {test.Top.generatedTop.source.sink.offComp.T63} {test.Top.generatedTop.source.sink.offComp.T64} {test.Top.generatedTop.source.sink.offComp.T65} {test.Top.generatedTop.source.sink.offComp.clk} {test.Top.generatedTop.source.sink.offComp.T66} {test.Top.generatedTop.source.sink.offComp.T67} {test.Top.generatedTop.source.sink.offComp.T68} {test.Top.generatedTop.source.sink.offComp.T69} {test.Top.generatedTop.source.sink.offComp.io_pcIn_bits_moduleId} {test.Top.generatedTop.source.sink.offComp.reset} {test.Top.generatedTop.source.sink.offComp.io_in_valid} {test.Top.generatedTop.source.sink.offComp.io_pcOut_valid} {test.Top.generatedTop.source.sink.offComp.io_in_bits_data} {test.Top.generatedTop.source.sink.offComp.T70} {test.Top.generatedTop.source.sink.offComp.T71} {test.Top.generatedTop.source.sink.offComp.T72} {test.Top.generatedTop.source.sink.offComp.T73} {test.Top.generatedTop.source.sink.offComp.T74} {test.Top.generatedTop.source.sink.offComp.io_pcIn_bits_pcValue} {test.Top.generatedTop.source.sink.offComp.T76} {test.Top.generatedTop.source.sink.offComp.T77} {test.Top.generatedTop.source.sink.offComp.T10} {test.Top.generatedTop.source.sink.offComp.T78} {test.Top.generatedTop.source.sink.offComp.T11} {test.Top.generatedTop.source.sink.offComp.T79} {test.Top.generatedTop.source.sink.offComp.T13} {test.Top.generatedTop.source.sink.offComp.T14} {test.Top.generatedTop.source.sink.offComp.T15} {test.Top.generatedTop.source.sink.offComp.R75} {test.Top.generatedTop.source.sink.offComp.SubState} {test.Top.generatedTop.source.sink.offComp.State} {test.Top.generatedTop.source.sink.offComp.T17} {test.Top.generatedTop.source.sink.offComp.R0} {test.Top.generatedTop.source.sink.offComp.T18} {test.Top.generatedTop.source.sink.offComp.T19} {test.Top.generatedTop.source.sink.offComp.R12} {test.Top.generatedTop.source.sink.offComp.R16} {test.Top.generatedTop.source.sink.offComp.R8} {test.Top.generatedTop.source.sink.offComp.io_in_bits_size} {test.Top.generatedTop.source.sink.offComp.T81} {test.Top.generatedTop.source.sink.offComp.T82} {test.Top.generatedTop.source.sink.offComp.T83} {test.Top.generatedTop.source.sink.offComp.T84} {test.Top.generatedTop.source.sink.offComp.T85} {test.Top.generatedTop.source.sink.offComp.io_out_bits_data} {test.Top.generatedTop.source.sink.offComp.T86} {test.Top.generatedTop.source.sink.offComp.T87} {test.Top.generatedTop.source.sink.offComp.io_pcOut_bits_pcType} {test.Top.generatedTop.source.sink.offComp.R80} {test.Top.generatedTop.source.sink.offComp.T88} {test.Top.generatedTop.source.sink.offComp.T21} {test.Top.generatedTop.source.sink.offComp.T89} {test.Top.generatedTop.source.sink.offComp.T22} {test.Top.generatedTop.source.sink.offComp.T23} {test.Top.generatedTop.source.sink.offComp.T24} {test.Top.generatedTop.source.sink.offComp.T25} {test.Top.generatedTop.source.sink.offComp.T26} {test.Top.generatedTop.source.sink.offComp.T27} {test.Top.generatedTop.source.sink.offComp.R20} {test.Top.generatedTop.source.sink.offComp.T28} {test.Top.generatedTop.source.sink.offComp.T29} }
gui_sg_addsignal -group ${offComp} { {test.Top.generatedTop.source.sink.offComp.io_pcOut_bits_pcValue} {test.Top.generatedTop.source.sink.offComp.io_pcIn_bits_pcType} {test.Top.generatedTop.source.sink.offComp.T90} {test.Top.generatedTop.source.sink.offComp.T91} {test.Top.generatedTop.source.sink.offComp.counter} {test.Top.generatedTop.source.sink.offComp.T92} {test.Top.generatedTop.source.sink.offComp.io_in_bits_addr} {test.Top.generatedTop.source.sink.offComp.T94} {test.Top.generatedTop.source.sink.offComp.T95} {test.Top.generatedTop.source.sink.offComp.T96} {test.Top.generatedTop.source.sink.offComp.T97} {test.Top.generatedTop.source.sink.offComp.T30} {test.Top.generatedTop.source.sink.offComp.T98} {test.Top.generatedTop.source.sink.offComp.T31} {test.Top.generatedTop.source.sink.offComp.T99} {test.Top.generatedTop.source.sink.offComp.T32} {test.Top.generatedTop.source.sink.offComp.T33} {test.Top.generatedTop.source.sink.offComp.R93} {test.Top.generatedTop.source.sink.offComp.T34} {test.Top.generatedTop.source.sink.offComp.T35} {test.Top.generatedTop.source.sink.offComp.T36} {test.Top.generatedTop.source.sink.offComp.T37} {test.Top.generatedTop.source.sink.offComp.io_pcOut_bits_request} {test.Top.generatedTop.source.sink.offComp.T38} {test.Top.generatedTop.source.sink.offComp.T39} {test.Top.generatedTop.source.sink.offComp.T1} {test.Top.generatedTop.source.sink.offComp.T2} {test.Top.generatedTop.source.sink.offComp.T3} {test.Top.generatedTop.source.sink.offComp.T4} {test.Top.generatedTop.source.sink.offComp.T5} {test.Top.generatedTop.source.sink.offComp.io_pcIn_valid} {test.Top.generatedTop.source.sink.offComp.T6} {test.Top.generatedTop.source.sink.offComp.T7} {test.Top.generatedTop.source.sink.offComp.T9} {test.Top.generatedTop.source.sink.offComp.io_pcOut_bits_portId} {test.Top.generatedTop.source.sink.offComp.io_out_ready} {test.Top.generatedTop.source.sink.offComp.T40} {test.Top.generatedTop.source.sink.offComp.T41} {test.Top.generatedTop.source.sink.offComp.T42} {test.Top.generatedTop.source.sink.offComp.T43} {test.Top.generatedTop.source.sink.offComp.T44} {test.Top.generatedTop.source.sink.offComp.T45} {test.Top.generatedTop.source.sink.offComp.T46} {test.Top.generatedTop.source.sink.offComp.T47} {test.Top.generatedTop.source.sink.offComp.T48} {test.Top.generatedTop.source.sink.offComp.io_pcIn_bits_portId} {test.Top.generatedTop.source.sink.offComp.T49} {test.Top.generatedTop.source.sink.offComp.io_in_ready} {test.Top.generatedTop.source.sink.offComp.io_pcIn_bits_request} {test.Top.generatedTop.source.sink.offComp.EmitReturnState} {test.Top.generatedTop.source.sink.offComp.T100} {test.Top.generatedTop.source.sink.offComp.io_in_bits_rw} {test.Top.generatedTop.source.sink.offComp.T101} {test.Top.generatedTop.source.sink.offComp.T102} {test.Top.generatedTop.source.sink.offComp.T103} {test.Top.generatedTop.source.sink.offComp.T104} }

# Global: Highlighting

# Post database loading setting...

# Restore C1 time
gui_set_time -C1_only 21500



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
catch {gui_list_expand -id ${Hier.1} test.Top}
catch {gui_list_expand -id ${Hier.1} test.Top.generatedTop}
catch {gui_list_expand -id ${Hier.1} test.Top.generatedTop.source}
catch {gui_list_expand -id ${Hier.1} test.Top.generatedTop.source.sink}
catch {gui_list_expand -id ${Hier.1} test.Top.generatedTop.source.sink.mainComp}
catch {gui_list_expand -id ${Hier.1} test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent}
catch {gui_list_select -id ${Hier.1} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp}}
gui_view_scroll -id ${Hier.1} -vertical -set 0
gui_view_scroll -id ${Hier.1} -horizontal -set 0

# Data 'Data.1'
gui_list_set_filter -id ${Data.1} -list { {Buffer 1} {Input 1} {Others 1} {Linkage 1} {Output 1} {Parameter 1} {All 1} {Aggregate 1} {Event 1} {Assertion 1} {Constant 1} {Interface 1} {Signal 1} {$unit 1} {Inout 1} {Variable 1} }
gui_list_set_filter -id ${Data.1} -text {*}
gui_list_show_data -id ${Data.1} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp}
gui_view_scroll -id ${Data.1} -vertical -set 0
gui_view_scroll -id ${Data.1} -horizontal -set 0
gui_view_scroll -id ${Hier.1} -vertical -set 0
gui_view_scroll -id ${Hier.1} -horizontal -set 0

# Source 'Source.1'
gui_src_value_annotate -id ${Source.1} -switch false
gui_set_env TOGGLE::VALUEANNOTATE 0
gui_open_source -id ${Source.1}  -replace -active test /proj/maysam/magilla/Gorilla++/chisel/Gorilla++/verilog/Top-harness-augmented-pageRank.v
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
gui_wv_zoom_timerange -id ${Wave.1} 0 41729
gui_list_set_filter -id ${Wave.1} -list { {Buffer 1} {Input 1} {Others 1} {Linkage 1} {Output 1} {Parameter 1} {All 1} {Aggregate 1} {Event 1} {Assertion 1} {Constant 1} {Interface 1} {Signal 1} {$unit 1} {Inout 1} {Variable 1} }
gui_list_set_filter -id ${Wave.1} -text {*mainoff_mem*}
gui_list_add_group -id ${Wave.1} -after {New Group} {{mainComp}}
gui_list_select -id ${Wave.1} {test.Top.generatedTop.source.sink.mainComp.gOffloadedComponent.mainComp.mainOff_mem_rep_valid }
gui_list_set_insertion_bar  -id ${Wave.1} -group {mainComp}  -position in
gui_seek_criteria -id ${Wave.1} {Any Edge}


gui_set_pref_value -category Wave -key exclusiveSG -value $groupExD
gui_list_set_height -id Wave -height $origWaveHeight
if {$origGroupCreationState} {
	gui_list_create_group_when_add -wave -enable
}
if { $groupExD } {
 gui_msg_report -code DVWW028
}
gui_marker_move -id ${Wave.1} {C1} 21500
gui_view_scroll -id ${Wave.1} -vertical -set 0

# View 'Wave.2'
gui_wv_sync -id ${Wave.2} -switch false
set groupExD [gui_get_pref_value -category Wave -key exclusiveSG]
gui_set_pref_value -category Wave -key exclusiveSG -value {false}
set origWaveHeight [gui_get_pref_value -category Wave -key waveRowHeight]
gui_list_set_height -id Wave -height 25
set origGroupCreationState [gui_list_create_group_when_add -wave]
gui_list_create_group_when_add -wave -disable
gui_marker_set_ref -id ${Wave.2}  C1
gui_wv_zoom_timerange -id ${Wave.2} 16277 26709
gui_list_set_filter -id ${Wave.2} -list { {Buffer 1} {Input 1} {Others 1} {Linkage 1} {Output 1} {Parameter 1} {All 1} {Aggregate 1} {Event 1} {Assertion 1} {Constant 1} {Interface 1} {Signal 1} {$unit 1} {Inout 1} {Variable 1} }
gui_list_set_filter -id ${Wave.2} -text {*out*}
gui_list_add_group -id ${Wave.2} -after {New Group} {{offComp}}
gui_list_select -id ${Wave.2} {test.Top.generatedTop.source.sink.offComp.io_out_valid }
gui_list_set_insertion_bar  -id ${Wave.2} -group {offComp}  -position in
gui_seek_criteria -id ${Wave.2} {Any Edge}



gui_set_env TOGGLE::DEFAULT_WAVE_WINDOW ${Wave.2}
gui_set_pref_value -category Wave -key exclusiveSG -value $groupExD
gui_list_set_height -id Wave -height $origWaveHeight
if {$origGroupCreationState} {
	gui_list_create_group_when_add -wave -enable
}
if { $groupExD } {
 gui_msg_report -code DVWW028
}
gui_marker_move -id ${Wave.2} {C1} 21500
gui_view_scroll -id ${Wave.2} -vertical -set 0

# DVE Active view and window setting: 

gui_set_active_window -window ${Wave.1}
gui_set_active_window -window ${Wave.1}
# Restore toplevel window zorder
# The toplevel window could be closed if it has no view/pane
if {[gui_exist_window -window ${TopLevel.1}]} {
	gui_set_active_window -window ${TopLevel.1} }
#</Session>

