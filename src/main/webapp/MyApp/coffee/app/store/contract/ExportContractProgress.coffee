Ext.define 'MyApp.store.contract.ExportContractProgress', 
	extend: 'Ext.data.Store'
	model: 'MyApp.model.contract.ExportContractProgress'
	autoLoad: 
		callback: (records, operation, success) ->
			if !success
				Ext.Msg.alert('提示', '加载数据失败')
	alias: 'exportContractProgressStore'
	pageSize: "#{MyApp.pageSize}"
	remoteSort: true
	sorters: [
		property: 'id'
		direction: 'DESC'
	]
	#listeners:
	#	beforeload: (store, operation, options)->
	#		console.log(operation)
	proxy:
		type: 'ajax'
		url: "#{MyApp.context}app/contract"
		startParam: '$offset'
		limitParam: '$limit'
		sortParam: '$sort'
		pageParam : '$page'
		filterParam: '$filter'
		# if override actionMethods use post get(read) datas
		#paramsAsJson: true
		reader:
			type: 'json'
			totalProperty: 'total'
			rootProperty: 'results'


