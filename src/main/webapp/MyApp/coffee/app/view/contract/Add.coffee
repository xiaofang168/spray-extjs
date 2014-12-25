Ext.define 'MyApp.view.contract.Add',
	extend : 'Ext.form.Panel'
	alias : 'widget.contractadd'
	collapsible: true  #可折叠
	autoScroll: true  #自动创建滚动条
	controller: 'contract'
	title: '添加出口合同进程'
	width: 800
	height: 500
	layout: 'form'
	defaults:
		xtype: 'container'
		layout: 'hbox'
		defaultType: 'textfield'
	fieldDefaults:
		labelSeparator :'：'#分隔符   
		labelWidth: 120
		labelAlign: 'right'
		maxWidth: 300
		width: 250
		allowBlank : false #是否允许为空   
		blankText : '不允许为空' 
		flex: 1
		margin: 5
		margin: '15 0 15 0' #(top, right, bottom, left).
	items:[
		items:[
			xtype: 'combo'
			fieldLabel:'月份'
			name:'month'
			store: new Ext.data.SimpleStore
				data: [
					['一月', '一月']
					['一月', '二月']
					['三月', '三月']
					['四月', '四月']
					['五月', '五月']
					['六月', '六月']
					['七月', '七月']
					['八月', '八月']
					['九月', '九月']
					['十月', '十月']
					['十一月', '十一月']
					['十二月', '十二月']
				]
				fields: ['value', 'text']
				valueField: 'value'
				displayField: 'text'
		,
			xtype: 'datefield'
			fieldLabel:'出口合同签订日期'
			name:'exportContractDate'
			format: 'Y-m-d'
		,
			fieldLabel:'出口合同号'
			name:'exportContractNum'
		]
	,
		items: [
			xtype: 'combo'
			fieldLabel: '合同状态'
			store: new Ext.data.SimpleStore
				data: [
					['待出运', '待出运']
					['已发货', '已发货']
					['生产中', '生产中']
					['等待发货中', '等待发货中']
					['采购中', '采购中']
				]
				fields: ['contractStatus', 'text']
				valueField: 'contractStatus'
				displayField: 'text'
		,
			fieldLabel: '客户方'
			name:'customer'
		,
			fieldLabel: '国别'
			name:'country'
		]
	,
		items: [
			fieldLabel: '合同产品'
			name:'product'
		,
			xtype: 'numberfield'
			fieldLabel: '出口合同金额'
			minValue: 0
			name:'exportContractMoney'
		,
			fieldLabel: '结算方式'
			name:'settlementWay'
		]
	,
		items: [
			fieldLabel: 'LC最晚交货期'
			allowBlank : true
			name:'latestDeliveryDate'
		,
			fieldLabel: '运输方式及箱型'
			name:'transportWay'
		,
			fieldLabel: '采购合同号'
			name:'purchaseContractNum'
		]
	,
		items: [
			xtype: 'numberfield'
			fieldLabel: '采购合同金额'
			minValue: 0
			name:'purchaseMoney'
		,
			fieldLabel: '合同进展'
			name:'progressDescription'
		,
			xtype: 'datefield'
			fieldLabel: '预计出厂日'
			name:'planLeaveFactoryDate'
			format: 'Y-m-d'
		]
	,
		items: [
			xtype: 'datefield'
			fieldLabel: '预计发货日'
			name:'planSendDate'
			format: 'Y-m-d'
		,
			xtype: 'datefield'
			fieldLabel: '预计到港日'
			name:'planArrivalData'
			format: 'Y-m-d'
		,
			fieldLabel: '是否已到款'
			name:'payDescription'
		]
	items: [
			fieldLabel: '备注'
			maxWidth: 700
			height: 50
			name:'comment'
		]
	]
	buttons:[
			text: '保存'
			handler : 'save'
		,
			text: '关闭'
			handler: ->
				@up('contractadd').hide()
	]
	buttonAlign : 'center'