const rps = (p1, p2) => {
    if (p1 === 'Scissors' && p2 === 'Rock')
        return 'P2 wins'

    return 'P1 wins'
}

module.exports = { rps }