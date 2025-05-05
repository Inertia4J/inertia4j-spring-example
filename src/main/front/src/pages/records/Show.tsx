import { useForm } from '@inertiajs/react';
import { Record } from './types'

export default function Show({ record }: { record: Record }) {
  const { delete: deleteRecord } = useForm();

  const handleDelete = () => {
    if (window.confirm(`Are you sure you want to delete ${record.name}?`)) {
      deleteRecord(`/records/${record.id}`);
    }
  };

  return (
    <main className="container">
      <a href="/records">Go back</a>
      <br/><br/>
      <h3>ID: {record.id}</h3>
      <h3>Name: {record.name}</h3>
      <button className="outline contrast" onClick={handleDelete}>Delete {record.name}</button>
    </main>
  )
}
