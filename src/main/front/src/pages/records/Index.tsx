import { Record } from './types'

export default function Index({ records }: { records: Record[] }) {
  return (
    <main className="container">
      <h1>Welcome to the Inertia4J Spring demo!</h1>
      <h4>You currently have {records.length} {records.length === 1 ? 'record' : 'records'}.</h4>

      {records.map(record => (
        <article key={record.id} style={{ display: 'flex', alignItems: 'center', marginBottom: '20px' }}>
          <div style={{ marginRight: '30px' }}>
            <img
              src={record.coverImage || '/path/to/default-image.jpg'} // Fallback image in case coverImage is null
              alt={`Cover of ${record.name}`}
              className="cover-image"
              style={{ maxWidth: '64px', maxHeight: '64px' }} // Ensures the image doesn't stretch
            />
          </div>
          <div style={{ flex: 1 }}>
            <h2 style={{ margin: 0 }}><a href={`/records/${record.id}`}>{record.name}</a></h2>
          </div>
        </article>
      ))}

      <a role="button" href="/records/new" style={{ marginTop: '20px', display: 'inline-block', textDecoration: 'none' }}>
        Create a new Record
      </a>
    </main>
  )
}
