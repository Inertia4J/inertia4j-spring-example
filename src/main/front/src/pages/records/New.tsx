import { FormEvent } from 'react'
import { useForm } from '@inertiajs/react'

export default function New() {
  const { data, setData, post, processing, errors } = useForm({
    name: '',
  }) 

  function handleSubmit(e: FormEvent) {
    e.preventDefault()
    post('/records')
  }

  return (
    <main className="container">
      <a href="/records">Go back</a>
      <br/><br/>
      <h1>Add Record</h1>
      <form onSubmit={handleSubmit}>
        <label htmlFor="name">Name</label>
        <input
          id="name"
          type="text"
          value={data.name}
          onChange={e => setData('name', e.target.value)}
        />
        {errors.name && <div className="text-red-500">{errors.name}</div>}
        <button type="submit" disabled={processing}>
          {processing ? 'Submitting...' : 'Submit'}
        </button>
      </form>
    </main>
  )
}
