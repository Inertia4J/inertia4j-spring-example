import { FormEvent } from 'react'
import { useForm } from '@inertiajs/react'
import { BackButton } from '../../components/BackButton'

export default function New() {
  const { data, setData, post, processing, errors } = useForm({
    name: '',
    artist: '',
    coverImage: '',
    yearOfRelease: '',
  })

  function handleSubmit(e: FormEvent) {
    e.preventDefault()
    post('/records')
  }

  return (
    <main className="container">
      <BackButton href="/records" />
      <br/><br/>
      <h1>Add Record</h1>
      <form onSubmit={handleSubmit}>
        <div className="grid gap-4 max-w-md">
          <div>
            <label htmlFor="name">Name</label>
            <input
              id="name"
              type="text"
              value={data.name}
              onChange={e => setData('name', e.target.value)}
            />
            {errors.name && <div className="text-red-500">{errors.name}</div>}
          </div>

          <div>
            <label htmlFor="artist">Artist</label>
            <input
              id="artist"
              type="text"
              value={data.artist}
              onChange={e => setData('artist', e.target.value)}
            />
            {errors.artist && <div className="text-red-500">{errors.artist}</div>}
          </div>

          <div>
            <label htmlFor="coverImage">Cover Image URL</label>
            <input
              id="coverImage"
              type="text"
              value={data.coverImage}
              onChange={e => setData('coverImage', e.target.value)}
            />
            {errors.coverImage && <div className="text-red-500">{errors.coverImage}</div>}
          </div>

          <div>
            <label htmlFor="yearOfRelease">Year of Release</label>
            <input
              id="yearOfRelease"
              type="number"
              value={data.yearOfRelease}
              onChange={e => setData('yearOfRelease', e.target.value)}
            />
            {errors.yearOfRelease && <div className="text-red-500">{errors.yearOfRelease}</div>}
          </div>
        </div>

        <button type="submit" disabled={processing}>
          {processing ? 'Submitting...' : 'Submit'}
        </button>
      </form>
    </main>
  )
}
