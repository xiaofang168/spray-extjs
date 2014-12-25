 Ext.define 'MyApp.view.main.MainModel',
	extend: 'Ext.app.ViewModel'
	alias: 'viewmodel.main'
	data:
		name: 'MyApp'
		systemMenu : [
			text : '系统办公'
			iconCls : ''
			expanded : true
			description : ''
			items : [
				text : '出口合同进程管理'
				module : 'Global'
				iconCls : 'fa fa-list'
				glyph: 0xf03a
				view:'contractlist'
			#,
			#	text : '合同管理'
			#	module : 'Project'
			#	iconCls : 'fa fa-search'
			#	glyph: 0xf002
			#	view: 'proxysearch'
			]
		]
	getMenus : ->
		items = []
		menuData = @get('systemMenu')
		Ext.Array.each menuData, (group)->
			submenu = []
			Ext.Array.each group.items, (menuitem) ->
				submenu.push(
						mainmenu : 'true'
						moduleName : menuitem.module
						text : menuitem.text
						iconCls : menuitem.iconCls
						glyph: menuitem.glyph
						view: menuitem.view
						handler : 'onMainMenuClick'
					)
			item =
				text : group.text
				menu : submenu
				iconCls : group.iconCls
				glyph: menuitem.glyph
			items.push(item)
		items
