Ext.define 'MyApp.model.proxy.Setting', 
	extend: 'Ext.data.Model'
	fields: ['app_name', 'ip','is_enable']
	proxy:
		type: 'rest'
		url : "#{MyApp.context}app/proxy"
		appendId : true