import { Record } from './types'

export default function Show({ record }: { record: Record }) {
  return (
    <main className="container">
      <a href="/records">Go back</a>
      <br/><br/>
      <h3>ID: {record.id}</h3>
      <h3>Name: {record.name}</h3>
    </main>
  )
}
