Ext.define 'MyApp.model.contract.ExportContractProgress', 
	extend: 'Ext.data.Model'
	fields: ['id'
			'month'
			'exportContractDate'
			'exportContractNum'
			'contractStatus'
			'customer'
			'country'
			'product'
			'exportContractMoney'
			'settlementWay'
			'latestDeliveryDate'
			'transportWay'
			'purchaseContractNum'
			'purchaseMoney'
			'progressDescription'
			'planLeaveFactoryDate'
			'planSendDate'
			'planArrivalData'
			'payDescription'
			'comment'
	]
	proxy:
		type: 'rest'
		url : "#{MyApp.context}app/contract"
		appendId : true