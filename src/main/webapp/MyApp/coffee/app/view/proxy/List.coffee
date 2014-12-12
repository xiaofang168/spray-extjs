Ext.define 'MyApp.view.proxy.List',
	extend : 'Ext.panel.Panel'
	requires: [
		'Ext.grid.Panel'#防止sencha app build production 打包运行找不到 gridpanel
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
		@items = [
			xtype: 'gridpanel'
			forceFit: true
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
			,
				text: '注册应用名称'
				sortable: true
				dataIndex: 'app_name'
				width: 2
			,
				text: '注册应用ip地址'
				dataIndex: 'ip'
				width: 5
			,
				text: '代理服务是否启用'
				dataIndex: 'is_enable'
				width: 2
			]
			store: Ext.create('MyApp.store.proxy.Setting')
		]
		@callParent()