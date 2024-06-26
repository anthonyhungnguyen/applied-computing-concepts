import { AutoComplete } from 'antd'
import axios from 'axios'
import { useEffect, useState } from 'react'

export default function AppAutoComplete({
    searchValue,
    setSearchValue,
}: {
    searchValue: string
    setSearchValue: (value: string) => void
}) {
    const [autoCompleteOptions, setAutoCompleteOptions] = useState<
        { value: string }[]
    >([])

    useEffect(() => {
        const fetchAutoCompleteData = async () => {
            try {
                const response = await axios.get('/api/auto-complete', {
                    params: { prefix: searchValue },
                })
                setAutoCompleteOptions(
                    response.data.map((item: string) => ({ value: item }))
                )
            } catch (error) {
                console.error('Fetch error:', error)
            }
        }
        fetchAutoCompleteData()
    }, [searchValue])
    return (
        <AutoComplete
            placeholder='Search food items'
            onSearch={(value) => setSearchValue(value)}
            style={{ marginBottom: 24, width: '200px' }}
            options={autoCompleteOptions}
            value={searchValue}
            onSelect={(value) => setSearchValue(value)}
            showSearch
        />
    )
}
