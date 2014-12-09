Ext.define 'MyApp.view.proxy.Add',
	extend : 'Ext.form.Panel'
	requires: ['MyApp.controller.proxy.ProxyController']
	alias : 'widget.proxyadd'
	title: '添加数据库代理配置'
	width: 800
	height: 400
	layout: 'form'
	defaultType: 'textfield'
	fieldDefaults:
		labelSeparator :'：'#分隔符   
		labelWidth: 70
		labelAlign: 'left'
		width:200
		allowBlank : false #是否允许为空   
		blankText : '不允许为空' 
	items:[
		xtype:'fieldset'
		title: '被代理应用信息'
		autoHeight:true
		collapsible: true #是否为可折叠  
		collapsed: false #默认是否折叠  
		layout:'column'
		border:true
		items:[
			columnWidth:.35 #该列在整行中所占百分比 
			border:false 
			items:[
				xtype:'textfield'
				fieldLabel:'应用名称'
				name:'app_name'
			,
				xtype:'textfield'
				fieldLabel:'应用名称'
				name:'app_name2'
			]
		,
			columnWidth:.35 #该列在整行中所占百分比
			border:false
			items: [
				xtype:'textfield'
				fieldLabel: 'ip地址'
				name:'ip'
			]
		,
			columnWidth:.28 #该列在整行中所占百分比 
			border:false
			items: [
				xtype:'textfield'
				fieldLabel:'服务是否启用'
				name:'is_enable'
				labelWidth: 100
				width: 140
			]
		]
	]
	buttons:[
			text: '保存'
			handler : 'save'
		,
			text: '关闭'
			handler: ->
				@up('proxyadd').hide()
	]
	buttonAlign : 'center'