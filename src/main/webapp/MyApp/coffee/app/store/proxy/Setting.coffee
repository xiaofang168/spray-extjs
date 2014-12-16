Ext.define 'MyApp.store.proxy.Setting', 
	extend: 'Ext.data.Store'
	model: 'MyApp.model.proxy.Setting'
	autoLoad: true
	alias: 'settingstore'
	pageSize: "#{MyApp.pageSize}"
	remoteSort: true
	sorters: [
		property: 'id'
		direction: 'DESC'
	]
	proxy:
		type: 'ajax'
		url: "#{MyApp.context}app/proxy"
		# if override actionMethods use post get(read) datas
		#paramsAsJson: true
		reader:
			type: 'json'
			rootProperty: 'users'
