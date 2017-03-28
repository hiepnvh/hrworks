Ext.define(App.path('store.TaskStatus'), {
			extend : 'Ext.data.Store',
			fields : ['id', 'value'],
			storeId : 'TaskStatus',
			data : [
				{id: 0 , value: 'Idle'},
				{id: 1 , value: 'Pending'},
				{id: 2 , value: 'Initialzing'},
				{id: 4 , value: 'Running'},
				{id: 8 , value: 'Cancelled'},
				{id: 16 , value: 'Finished'},
				{id: 32 , value: 'Suspened'}
		     ],
		     sorters: [{
		     property: 'value',
		     direction: 'ASC' // or 'ASC'or DESC
		   }]
		});