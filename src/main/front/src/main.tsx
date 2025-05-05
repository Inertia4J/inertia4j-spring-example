import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { createInertiaApp } from '@inertiajs/react'
import '@picocss/pico/css/pico.violet.min.css';
import './assets/main.scss';

createInertiaApp({
  resolve: name => {
    const pages = import.meta.glob('./pages/**/*.tsx', { eager: true })
    return pages[`./pages/${name}.tsx`]
},
  setup({ el, App, props }) {
    createRoot(el).render(
      <StrictMode>
        <App {...props} />
      </StrictMode>
    )
  },
})
