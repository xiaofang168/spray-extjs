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
		glyph: 0xf055
		handler : 'addProxySetting'
	,
		xtype : 'tbspacer'
		width: 20
	,
		xtype: 'button'
		text: '修改'
		glyph: 0xf044
		handler: ''
	,
		xtype : 'tbspacer'
		width: 20
	,
		xtype: 'button'
		text: '删除'
		glyph: 0xf00d
		handler: ''
	]
	items: [
		xtype: 'gridpanel'
		selType: 'checkboxmodel'
		selModel:
			injectCheckbox: 0
			mode: 'SIMPLE' #"SINGLE"/"SIMPLE"/"MULTI"
			checkOnly: true
		reference: 'proxygrid'
		region: 'center'
		forceFit: true
		columns: [
			xtype:'rownumberer'
			sortable: false
			width: 50
			text:'序号'
			sortable: false
			locked: true
		,
			text: '注册应用名称'
			sortable: true
			#text: '注册应用名称'
			dataIndex: 'app_name'
		,
			text: '注册应用ip地址'
			dataIndex: 'ip'
		,
			text: '代理服务是否启用'
			dataIndex: 'is_enable'
		]
		store: Ext.create('MyApp.store.proxy.Setting')
	]