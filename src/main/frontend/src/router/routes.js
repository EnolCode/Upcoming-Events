
const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '',  name: 'index', component: () => import('pages/IndexPage.vue') },
      { path: 'form',name:'formLog', component: () => import('pages/FormPage.vue') },
      { path: 'formAdd',name:'formAdd', component: () => import('pages/FormAddPage.vue') },
      { path: 'dashboard',name:'dashboard', component: () => import('pages/Dashboard.vue') }
    ]
  },

  // Always leave this as last one,
  
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue')
  }
]

export default routes
