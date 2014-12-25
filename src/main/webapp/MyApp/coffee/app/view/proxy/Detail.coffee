Ext.define 'MyApp.view.proxy.Detail',
	extend : 'Ext.form.Panel'
	alias : 'widget.proxyedetail'
	controller: 'proxy'
	#frame: true
	title: '数据库代理详细信息'
	width: 800
	height: 400
	layout:'column'
	defaults:
		xtype: 'container'
		layout: 'form'
		frame: true
		defaultType: 'displayfield'
	items:[
		columnWidth: 0.3
		items:[
				fieldLabel:'应用名称'
				name:'appName'
				value: 'sadfsadfa'
			,
				fieldLabel:'ip'
				name:'appName'
				value: 'localhost'
			,
				fieldLabel:'是否启用'
				name:'appName'
				value: '1'
		]
	,
		columnWidth: 0.3
		items:[
			fieldLabel:'应用名称'
			name:'appName'
			value: '1111111111'
		]
	,
		columnWidth: 0.3
		items:[
			fieldLabel:'应用名称'
			name:'appName'
			value: '1111111111'
		]
	]
	buttons:[
			text: '关闭'
			handler: ->
				@up('proxyedetail').hide()
	]
	buttonAlign : 'center'