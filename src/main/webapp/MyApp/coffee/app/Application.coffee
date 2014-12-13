Ext.define 'MyApp.Application',
	extend: 'Ext.app.Application'
	name: 'MyApp'
	stores: [
		# TODO: add global / shared stores here
	]
	launch: ->
		MyApp.context = '/'
		# TODO - Launch the application