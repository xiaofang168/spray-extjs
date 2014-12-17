Ext.define 'MyApp.view.proxy.List',
	extend : 'Ext.panel.Panel'
	requires: [
		'Ext.grid.Panel'#防止sencha app build production 打包运行找不到 gridpanel
		'Ext.button.Button'
		'Ext.toolbar.Spacer'
		'Ext.toolbar.Fill'
		'Ext.selection.CheckboxModel'
		'MyApp.store.proxy.Setting'
		'Ext.toolbar.Paging'
		'MyApp.view.proxy.ProxyController'
	]
	alias : 'widget.proxylist'
	controller: 'proxy'
	closeAction : 'hide'
	autoDestroy : false
	tabPosition : 'top'
	title: '代理配置列表'
	closable : true
	reorderable : true
	tbar:[
		xtype: 'button'
		text: '添加'
		#icon: '../../../../resources/images/icons/fam/add.png'
		glyph: 0xf0fe
		handler : 'addProxySetting'
	,
		xtype : 'tbspacer'
		width: 20
	,
		xtype: 'button'
		text: '修改'
		glyph: 0xf044
		handler: 'edit'
	,
		xtype : 'tbspacer'
		width: 20
	,
		xtype: 'button'
		text: '删除'
		glyph: 0xf00d
		handler: 'delete'
	,
		xtype: 'tbfill'
	,
		xtype: 'button'
		text: '导出Excel'
		glyph: 0xf1c3
		handler: 'exportExcel'
	]
	initComponent: ->
		settingstore = Ext.create('MyApp.store.proxy.Setting')
		@items = [
			xtype: 'gridpanel'
			forceFit: true
			loadMask: true #读取数据遮罩和提示,Loading....
			selType: 'checkboxmodel'
			selModel:
				injectCheckbox: 0
				mode: 'SIMPLE' #"SINGLE"/"SIMPLE"/"MULTI"
				checkOnly: true
			reference: 'proxygrid'
			region: 'center'
			columns: [
				xtype:'rownumberer'
				sortable: false
				text:'序号'
				width: 60
			,
				hidden : true
				dataIndex: 'id'
				hideable: false
			,
				text: '注册应用名称'
				sortable: true
				dataIndex: 'appName'
				#menuDisabled: true
			,
				text: '注册应用ip地址'
				dataIndex: 'ip'
			,
				text: '代理服务是否启用'
				dataIndex: 'isEnable'
			]
			store: settingstore
			dockedItems: [
				xtype: 'pagingtoolbar'
				store: settingstore 
				dock: 'bottom'
				displayInfo: true
			]
		]
		@callParent()