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
		text: '添加代理配置'
		icon: '../../../../resources/images/icons/fam/add.png'
		handler : 'addProxySetting'
	]
	items: [
		xtype: 'gridpanel'
		reference: 'proxygrid'
		region: 'center'
		forceFit: true
		columns: [
			text: '注册应用名称'
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