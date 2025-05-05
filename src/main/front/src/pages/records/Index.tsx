import { Record } from './types'

export default function Index({ records }: { records: Record[] }) {
  return (
    <main className="container">
      <h1>You got {records.length} records.</h1>

      <ul>
        {records.map(record => (
          <li key={record.id}>
            <a href={`/records/${record.id}`}>{record.name}</a>
          </li>
        ))}
      </ul>

      <a role="button" href="/records/new">Create a new Record</a>
    </main>
  )
}
